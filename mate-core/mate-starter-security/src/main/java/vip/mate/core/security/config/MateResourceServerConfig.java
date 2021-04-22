package vip.mate.core.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import vip.mate.core.security.handle.MateAccessDeniedHandler;
import vip.mate.core.security.handle.MateAuthenticationEntryPoint;

/**
 * 资源服务配置
 *
 * @author pangu
 */
@Order(5)
@EnableResourceServer
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class MateResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final IgnoreUrlPropsConfiguration ignoreUrlPropsConfig;

	private final RedisConnectionFactory redisConnectionFactory;

	/**
	 * 配置token存储到redis中
	 */
	@Bean
	public RedisTokenStore redisTokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config
				= http.requestMatchers().anyRequest()
				.and()
				.authorizeRequests();
		ignoreUrlPropsConfig.getUrls().forEach(url -> {
			config.antMatchers(url).permitAll();
		});
		ignoreUrlPropsConfig.getIgnoreSecurity().forEach(url -> {
			config.antMatchers(url).permitAll();
		});
		config
				//任何请求
				.anyRequest()
				//都需要身份认证
				.authenticated()
				//csrf跨站请求
				.and()
				.csrf().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(new MateAuthenticationEntryPoint())
				.accessDeniedHandler(new MateAccessDeniedHandler());
	}
}
