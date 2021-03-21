package vip.mate.core.security.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.util.StringPool;
import vip.mate.core.security.handle.MateAccessDeniedHandler;
import vip.mate.core.security.handle.MateAuthenticationEntryPoint;
import vip.mate.core.security.props.MateSecurityProperties;

@EnableResourceServer
@EnableConfigurationProperties({MateSecurityProperties.class})
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class MateResourceServerConfig extends ResourceServerConfigurerAdapter {

	private MateSecurityProperties properties;
	private MateAccessDeniedHandler accessDeniedHandler;
	private MateAuthenticationEntryPoint exceptionEntryPoint;

	@Autowired(required = false)
	public void setProperties(MateSecurityProperties properties) {
		this.properties = properties;
	}

	@Autowired(required = false)
	public void setAccessDeniedHandler(MateAccessDeniedHandler accessDeniedHandler) {
		this.accessDeniedHandler = accessDeniedHandler;
	}

	@Autowired(required = false)
	public void setExceptionEntryPoint(MateAuthenticationEntryPoint exceptionEntryPoint) {
		this.exceptionEntryPoint = exceptionEntryPoint;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		if (properties == null) {
			permitAll(http);
			return;
		}
		String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUris(), StringPool.COMMA);
		if (ArrayUtils.isEmpty(anonUrls)) {
			anonUrls = new String[]{};
		}
		if (ArrayUtils.contains(anonUrls, Oauth2Constant.ALL)) {
			permitAll(http);
			return;
		}
		http.csrf().disable()
				.requestMatchers().antMatchers(properties.getAuthUri())
				.and()
				.authorizeRequests()
				.antMatchers(anonUrls).permitAll()
				.antMatchers(properties.getAuthUri()).authenticated()
				.and()
				.httpBasic();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		if (exceptionEntryPoint != null) {
			resources.authenticationEntryPoint(exceptionEntryPoint);
		}
		if (accessDeniedHandler != null) {
			resources.accessDeniedHandler(accessDeniedHandler);
		}
	}

	private void permitAll(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().anyRequest().permitAll();
	}
}
