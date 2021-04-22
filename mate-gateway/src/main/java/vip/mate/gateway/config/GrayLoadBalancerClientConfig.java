package vip.mate.gateway.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.config.GatewayReactiveLoadBalancerClientAutoConfiguration;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.gateway.filter.GrayReactiveLoadBalancerClientFilter;
import vip.mate.gateway.rule.GrayLoadBalancer;
import vip.mate.gateway.rule.VersionGrayLoadBalancer;

/**
 * 灰度负载模式自动装配
 *
 * @author L.cm
 * @author madi
 * @date 2021-02-24 13:41
 */
@Configuration
@EnableConfigurationProperties(GatewayLoadBalancerProperties.class)
@ConditionalOnProperty(value = "gray.rule.enabled", havingValue = "true")
@AutoConfigureBefore(GatewayReactiveLoadBalancerClientAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class GrayLoadBalancerClientConfig {

	@Bean
	public ReactiveLoadBalancerClientFilter gatewayLoadBalancerClientFilter(GrayLoadBalancer grayLoadBalancer,
	                                                                        GatewayLoadBalancerProperties properties) {
		return new GrayReactiveLoadBalancerClientFilter(properties, grayLoadBalancer);
	}

	@Bean
	public GrayLoadBalancer grayLoadBalancer(DiscoveryClient discoveryClient) {
		return new VersionGrayLoadBalancer(discoveryClient);
	}
}
