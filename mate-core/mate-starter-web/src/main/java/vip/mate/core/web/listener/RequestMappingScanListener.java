package vip.mate.core.web.listener;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.util.StringPool;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.redis.core.RedisService;

import java.util.*;

/**
 * 请求资源扫描监听器
 *
 * @author pangu
 */
@Slf4j
public class RequestMappingScanListener implements ApplicationListener<ApplicationReadyEvent> {
	private static final AntPathMatcher pathMatch = new AntPathMatcher();
	private final Set<String> ignoreApi = new HashSet<String>();
	private final RedisService redisService;

	/**
	 * 构造方法
	 *
	 * @param redisService 注入redis
	 */
	public RequestMappingScanListener(RedisService redisService) {
		this.redisService = redisService;
		this.ignoreApi.add("/error");
		this.ignoreApi.add("/swagger-resources/**");
		this.ignoreApi.add("/v3/api-docs-ext/**");
		this.ignoreApi.add("/provider/**");
	}

	/**
	 * 默认事件
	 *
	 * @param event ApplicationReadyEvent
	 */
	@Override
	public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {

		try {
			ConfigurableApplicationContext applicationContext = event.getApplicationContext();
			Environment env = applicationContext.getEnvironment();
			// 获取微服务模块名称
			String microService = env.getProperty("spring.application.name", "application");
			if (redisService == null || applicationContext.containsBean("resourceServerConfiguration")) {
				log.warn("[{}]忽略接口资源扫描", microService);
				return;
			}

			// 所有接口映射
			RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
			// 获取url与类和方法的对应信息
			Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
				RequestMappingInfo info = m.getKey();
				HandlerMethod method = m.getValue();
//				if (method.getMethodAnnotation(ApiIgnore.class) != null) {
//					// 忽略的接口不扫描
//					continue;
//				}
				// 请求路径
				PatternsRequestCondition p = info.getPatternsCondition();
				String urls = getUrls(p.getPatterns());
				if (isIgnore(urls)) {
					continue;
				}
				Set<MediaType> mediaTypeSet = info.getProducesCondition().getProducibleMediaTypes();
				for (MethodParameter params : method.getMethodParameters()) {
					if (params.hasParameterAnnotation(RequestBody.class)) {
						mediaTypeSet.add(MediaType.APPLICATION_JSON_UTF8);
						break;
					}
				}
				String mediaTypes = getMediaTypes(mediaTypeSet);
				// 请求类型
				RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
				String methods = getMethods(methodsCondition.getMethods());
				Map<String, String> api = Maps.newHashMap();
				// 类名
				String className = method.getMethod().getDeclaringClass().getName();
				// 方法名
				String methodName = method.getMethod().getName();
				String returnName = method.getReturnType().getParameterType().getName();
				if (null != returnName) {
					returnName = returnName.substring(returnName.lastIndexOf(StringPool.DOT) + 1, returnName.length());
				}
				// md5码
				String md5 = DigestUtils.md5DigestAsHex((microService + urls).getBytes());
				String name = "";
				String description = "";
				String auth = "1";

				Operation operation = method.getMethodAnnotation(Operation.class);
				if (operation != null) {
					name = operation.summary();
					description = operation.description();
				}
				// 判断是否需要权限校验
				PreAuth preAuth = method.getMethodAnnotation(PreAuth.class);
				if (preAuth != null) {
					auth = "0";
				}
				name = StringUtil.isBlank(name) ? methodName : name;
				api.put("name", name);
				api.put("notes", description);
				api.put("path", urls);
				api.put("code", md5);
				api.put("className", className);
				api.put("methodName", methodName);
				api.put("method", methods);
				api.put("serviceId", microService);
				api.put("contentType", returnName);
				api.put("auth", auth);
				list.add(api);
			}
			// 放入redis缓存
			Map<String, Object> res = Maps.newHashMap();
			res.put("serviceId", microService);
			res.put("size", list.size());
			res.put("list", list);
			redisService.hset(MateConstant.MATE_API_RESOURCE, microService, res, 18000L);
			redisService.sSetAndTime(MateConstant.MATE_SERVICE_RESOURCE, 18000L, microService);
			log.info("资源扫描结果:serviceId=[{}] size=[{}] redis缓存key=[{}]", microService, list.size(), MateConstant.MATE_API_RESOURCE);
		} catch (Exception e) {
			log.error("error: {}", e.getMessage());
		}


	}

	private String getUrls(Set<String> urls) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String url : urls) {
			stringBuilder.append(url).append(",");
		}
		if (urls.size() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

	/**
	 * 是否是忽略的Api
	 *
	 * @param requestPath 请求地址
	 * @return boolean
	 */
	private boolean isIgnore(String requestPath) {
		for (String path : ignoreApi) {
			if (pathMatch.match(path, requestPath)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取媒体类型
	 *
	 * @param mediaTypes 类型SET集
	 * @return String
	 */
	private String getMediaTypes(Set<MediaType> mediaTypes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (MediaType mediaType : mediaTypes) {
			stringBuilder.append(mediaType.toString()).append(",");
		}
		if (mediaTypes.size() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}

	/**
	 * 获取方法
	 *
	 * @param requestMethods 请求方法
	 * @return String
	 */
	private String getMethods(Set<RequestMethod> requestMethods) {
		StringBuilder stringBuilder = new StringBuilder();
		for (RequestMethod requestMethod : requestMethods) {
			stringBuilder.append(requestMethod.toString()).append(",");
		}
		if (requestMethods.size() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		return stringBuilder.toString();
	}
}
