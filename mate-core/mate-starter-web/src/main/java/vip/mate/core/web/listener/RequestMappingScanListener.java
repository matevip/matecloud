package vip.mate.core.web.listener;

import com.google.common.collect.Maps;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.annotations.ApiIgnore;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.redis.core.RedisService;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
		this.ignoreApi.add("/v2/api-docs-ext/**");
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
				if (method.getMethodAnnotation(ApiIgnore.class) != null) {
					// 忽略的接口不扫描
					continue;
				}
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
				String fullName = className + "." + methodName;
				String name = "";
				String desc = "";

				ApiOperation apiOperation = method.getMethodAnnotation(ApiOperation.class);
				if (apiOperation != null) {
					name = apiOperation.value();
					desc = apiOperation.notes();
				}
				name = StringUtil.isBlank(name) ? methodName : name;
				api.put("apiName", name);
				api.put("apiDesc", desc);
				api.put("path", urls);
				api.put("className", className);
				api.put("methodName", methodName);
				api.put("requestMethod", methods);
				api.put("serviceId", microService);
				api.put("contentType", mediaTypes);
				list.add(api);
				log.info("api scan: {}", api);
			}
			// 放入redis缓存
			Map<String,Object> res = Maps.newHashMap();
			res.put("serviceId",microService);
			res.put("size",list.size());
			res.put("list",list);
			redisService.hset(MateConstant.API_RESOURCE, microService, res);
		} catch (Exception e) {
			log.error("error: {}", e.getMessage());
		}


	}

	private String getUrls(Set<String> urls) {
		StringBuilder sbf = new StringBuilder();
		for (String url : urls) {
			sbf.append(url).append(",");
		}
		if (urls.size() > 0) {
			sbf.deleteCharAt(sbf.length() - 1);
		}
		return sbf.toString();
	}

	/**
	 * 是否是忽略的Api
	 *
	 * @param requestPath 请求地址
	 * @return boolean
	 */
	private boolean isIgnore(String requestPath) {
		Iterator<String> it = ignoreApi.iterator();
		while (it.hasNext()) {
			String path = it.next();
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
		StringBuilder sbf = new StringBuilder();
		for (MediaType mediaType : mediaTypes) {
			sbf.append(mediaType.toString()).append(",");
		}
		if (mediaTypes.size() > 0) {
			sbf.deleteCharAt(sbf.length() - 1);
		}
		return sbf.toString();
	}

	/**
	 * 获取方法
	 *
	 * @param requestMethods 请求方法
	 * @return String
	 */
	private String getMethods(Set<RequestMethod> requestMethods) {
		StringBuilder sbf = new StringBuilder();
		for (RequestMethod requestMethod : requestMethods) {
			sbf.append(requestMethod.toString()).append(",");
		}
		if (requestMethods.size() > 0) {
			sbf.deleteCharAt(sbf.length() - 1);
		}
		return sbf.toString();
	}
}
