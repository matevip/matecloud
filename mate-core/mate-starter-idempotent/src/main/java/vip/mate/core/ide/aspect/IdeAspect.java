package vip.mate.core.ide.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.mate.core.common.aspect.BaseAspect;
import vip.mate.core.ide.annotation.Ide;
import vip.mate.core.ide.enums.IdeTypeEnum;
import vip.mate.core.ide.exception.IdeException;
import vip.mate.core.redis.core.RedisService;

import javax.servlet.http.HttpServletRequest;

/**
 * 注解执行器 处理重复请求 和串行指定条件的请求
 * <p>
 * 两种模式的拦截
 * 1.rid 是针对每一次请求的
 * 2.key+val 是针对相同参数请求
 * </p>
 * <p>
 * 另根据谢新的建议对所有参数进行加密检验，提供思路，可以自行扩展
 * DigestUtils.md5Hex(userId + "-" + request.getRequestURL().toString()+"-"+ JSON.toJSONString(request.getParameterMap()));
 * 或 DigestUtils.md5Hex(ip + "-" + request.getRequestURL().toString()+"-"+ JSON.toJSONString(request.getParameterMap()));
 * </p>
 *
 * @author pangu
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@ConditionalOnClass(RedisService.class)
public class IdeAspect extends BaseAspect {

	private final ThreadLocal<String> PER_FIX_KEY = new ThreadLocal<String>();

	/**
	 * 配置注解后 默认开启
	 */
	private final boolean enable = true;

	/**
	 * request请求头中的key
	 */
	private final static String HEADER_RID_KEY = "RID";

	/**
	 * redis中锁的key前缀
	 */
	private static final String REDIS_KEY_PREFIX = "RID:";

	/**
	 * 锁等待时长
	 */
	private static final int LOCK_WAIT_TIME = 10;

	private final RedisService redisService;

	@Pointcut("@annotation(vip.mate.core.ide.annotation.Ide)")
	public void watchIde() {

	}

	@Before("watchIde()")
	public void doBefore(JoinPoint joinPoint) {
		Ide ide = getAnnotation(joinPoint, Ide.class);

		if (enable && null != ide) {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if (null == attributes) {
				throw new IdeException("请求数据为空");
			}
			HttpServletRequest request = attributes.getRequest();

			//1.判断模式
			if (ide.ideTypeEnum() == IdeTypeEnum.ALL || ide.ideTypeEnum() == IdeTypeEnum.RID) {
				//2.1.通过rid模式判断是否属于重复提交
				String rid = request.getHeader(HEADER_RID_KEY);

				if (StringUtils.isNotBlank(rid)) {
					Boolean result = redisService.tryLock(REDIS_KEY_PREFIX + rid, LOCK_WAIT_TIME);
					if (!result) {
						throw new IdeException("命中RID重复请求");
					}
					log.debug("msg1=当前请求已成功记录,且标记为0未处理,,{}={}", HEADER_RID_KEY, rid);
				} else {
					log.warn("msg1=header没有rid,防重复提交功能失效,,remoteHost={}" + request.getRemoteHost());
				}
			}

			if (ide.ideTypeEnum() == IdeTypeEnum.ALL
					|| ide.ideTypeEnum() == IdeTypeEnum.KEY) {
				//2.2.通过自定义key模式判断是否属于重复提交
				String key = ide.key();
				if (StringUtils.isNotBlank(key)) {
					String val = "";
					Object[] paramValues = joinPoint.getArgs();
					String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
					//获取自定义key的value
					for (int i = 0; i < paramNames.length; i++) {
						String params = JSON.toJSONString(paramValues[i]);
						if (params.startsWith("{")) {
							//如果是对象
							//通过key获取value
							JSONObject jsonObject = JSON.parseObject(params);
							val = jsonObject.getString(key);
						} else if (key.equals(paramNames[i])) {
							//如果是单个k=v
							val = params;
						} else {
							//如果自定义的key,在请求参数中没有此参数,说明非法请求
							log.warn("自定义的key,在请求参数中没有此参数,防重复提交功能失效");
						}
					}

					//判断重复提交的条件
					String perFix = ide.perFix();
					if (StringUtils.isNotBlank(val)) {
						perFix = perFix + ":" + val;

						try {
							Boolean result = redisService.tryLock(perFix, LOCK_WAIT_TIME);
							if (!result) {
								String targetName = joinPoint.getTarget().getClass().getName();
								String methodName = joinPoint.getSignature().getName();
								log.error("msg1=不允许重复执行,,key={},,targetName={},,methodName={}", perFix, targetName, methodName);
								throw new IdeException("不允许重复提交");
							}
							//存储在当前线程
							PER_FIX_KEY.set(perFix);
							log.info("msg1=当前请求已成功锁定:{}", perFix);
						} catch (Exception e) {
							log.error("获取redis锁发生异常", e);
							throw e;
						}
					} else {
						log.warn("自定义的key,在请求参数中value为空,防重复提交功能失效");
					}
				}
			}
		}
	}

	@After("watchIde()")
	public void doAfter(JoinPoint joinPoint) throws Throwable {
		try {
			Ide ide = getAnnotation(joinPoint, Ide.class);
			if (enable && null != ide) {

				if (ide.ideTypeEnum() == IdeTypeEnum.ALL
						|| ide.ideTypeEnum() == IdeTypeEnum.RID) {
					ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
					HttpServletRequest request = attributes.getRequest();
					String rid = request.getHeader(HEADER_RID_KEY);
					if (StringUtils.isNotBlank(rid)) {
						try {
//							redisService.unLock(REDIS_KEY_PREFIX + rid);
							log.info("msg1=当前请求已成功处理,,rid={}", rid);
						} catch (Exception e) {
							log.error("释放redis锁异常", e);
						}
					}
					PER_FIX_KEY.remove();
				}

				if (ide.ideTypeEnum() == IdeTypeEnum.ALL
						|| ide.ideTypeEnum() == IdeTypeEnum.KEY) {
					// 自定义key
					String key = ide.key();
					if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(PER_FIX_KEY.get())) {
						try {
//							redisService.unLock(PER_FIX_KEY.get());
							log.info("msg1=当前请求已成功释放,,key={}", PER_FIX_KEY.get());
							PER_FIX_KEY.set(null);
							PER_FIX_KEY.remove();
						} catch (Exception e) {
							log.error("释放redis锁异常", e);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
