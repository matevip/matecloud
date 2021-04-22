package vip.mate.core.gray.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import vip.mate.core.gray.rule.GrayRoundRobinLoadBalancer;

/**
 * 灰度负载配置类
 *
 * @author madi
 */
public class GrayLoadbalancerConfig {

	@Bean
	public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
	                                                                               LoadBalancerClientFactory loadBalancerClientFactory) {
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new GrayRoundRobinLoadBalancer(
				loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
	}


}