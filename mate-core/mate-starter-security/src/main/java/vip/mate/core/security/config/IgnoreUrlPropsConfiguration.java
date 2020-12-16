package vip.mate.core.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 忽略URL属性配置
 *
 * @author pangu
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "mate.security.ignore")
@Component
public class IgnoreUrlPropsConfiguration {

	/**
	 * 认证中心默认忽略验证地址
	 */
	private static final String[] SECURITY_ENDPOINTS = {
			"/auth/**",
			"/oauth/token",
			"/login/*",
			"/actuator/**",
			"/v2/api-docs",
			"/doc.html",
			"/webjars/**",
			"**/favicon.ico",
			"/swagger-resources/**"
	};

	private List<String> urls = new ArrayList<>();

	private List<String> client = new ArrayList<>();

	private List<String> ignoreSecurity = new ArrayList<>();

	/**
	 * 首次加载合并ENDPOINTS
	 */
	@PostConstruct
	public void initIgnoreSecurity() {
		Collections.addAll(ignoreSecurity, SECURITY_ENDPOINTS);
	}

}
