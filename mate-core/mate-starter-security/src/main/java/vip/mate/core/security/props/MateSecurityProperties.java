package vip.mate.core.security.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import vip.mate.core.common.constant.Oauth2Constant;

@Getter
@Setter
@RefreshScope
@ConfigurationProperties(prefix = "mate.cloud.security")
public class MateSecurityProperties {

	/**
	 * 是否开启安全配置
	 */
	private Boolean enable;

	/**
	 * 配置需要认证的uri，默认为所有/**
	 */
	private String authUri = Oauth2Constant.ALL;

	/**
	 * 免认证资源路径，支持通配符
	 * 多个值时使用逗号分隔
	 */
	private String anonUris;

	/**
	 * 是否只能通过网关获取资源
	 */
	private Boolean onlyFetchByGateway = Boolean.TRUE;
}
