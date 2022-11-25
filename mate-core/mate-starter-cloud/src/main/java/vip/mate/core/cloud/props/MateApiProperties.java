package vip.mate.core.cloud.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 验证权限配置
 *
 * @author pangu
 * @date 2020-10-28
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "mate.uaa")
public class MateApiProperties {

	/**
	 * 监控中心和swagger需要访问的url
	 */
	private static final String[] ENDPOINTS = {
			"/oauth/**",
			"/actuator/**",
			"/v2/api-docs/**",
			"/v3/api-docs/**",
			"/swagger/api-docs",
			"/swagger-ui.html",
			"/doc.html",
			"/swagger-resources/**",
			"/webjars/**",
			"/druid/**",
			"/error/**",
			"/assets/**",
			"/auth/logout",
			"/auth/code",
			"/auth/sms-code"
	};

	/**
	 * 忽略URL，List列表形式
	 */
	private List<String> ignoreUrl = new ArrayList<>();

	/**
	 * 是否启用网关鉴权模式
	 */
	private Boolean enable = false;

	/**
	 * 首次加载合并ENDPOINTS
	 */
	@PostConstruct
	public void initIgnoreUrl() {
		Collections.addAll(ignoreUrl, ENDPOINTS);
	}
}
