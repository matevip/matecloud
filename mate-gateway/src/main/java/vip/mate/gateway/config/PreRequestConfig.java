package vip.mate.gateway.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.cloud.props.MateRequestProperties;

/**
 * 预请求配置
 *
 * @author pangu
 */
@Configuration
@EnableConfigurationProperties({MateRequestProperties.class})
public class PreRequestConfig {
}
