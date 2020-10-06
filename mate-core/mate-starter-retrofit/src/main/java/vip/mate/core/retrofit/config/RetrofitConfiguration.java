package vip.mate.core.retrofit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.retrofit.interceptor.SignInterceptor;

/**
 * Retrofit配置类
 *
 * @author pangu
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-retrofit.yml")
public class RetrofitConfiguration {

	@Bean
	public SignInterceptor signInterceptor() {
		return new SignInterceptor();
	}

}
