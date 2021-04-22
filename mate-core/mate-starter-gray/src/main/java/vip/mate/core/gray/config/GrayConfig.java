package vip.mate.core.gray.config;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.gray.fegin.GrayFeignRequestInterceptor;

/**
 * 灰度配置类
 *
 * @author madi
 * @date 2021-03-02 18:30
 */
@Configuration
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
@LoadBalancerClients(defaultConfiguration = GrayLoadbalancerConfig.class)
public class GrayConfig {
	@Bean
	public RequestInterceptor grayFeignRequestInterceptor() {
		return new GrayFeignRequestInterceptor();
	}
}
