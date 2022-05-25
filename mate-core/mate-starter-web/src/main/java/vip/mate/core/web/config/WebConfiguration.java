package vip.mate.core.web.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vip.mate.core.redis.core.RedisService;
import vip.mate.core.web.support.LoginUserArgumentResolver;

import java.util.List;

/**
 * WEB配置类
 *
 * @author pangu
 */
@Slf4j
@AutoConfiguration
@AllArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

	private final RedisService redisService;

	/**
	 * Token参数解析
	 *
	 * @param argumentResolvers 解析类
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//注入用户信息
		argumentResolvers.add(new LoginUserArgumentResolver());
	}

	/**
	 * 资源扫描监听器类
	 *
	 * @return RequestMappingScanListener
	 */
//	@Bean
//	@ConditionalOnMissingBean(RequestMappingScanListener.class)
//	public RequestMappingScanListener resourceAnnotationScan() {
//		RequestMappingScanListener scan = new RequestMappingScanListener(redisService);
//		log.info("资源扫描类.[{}]", scan);
//		return scan;
//	}
}
