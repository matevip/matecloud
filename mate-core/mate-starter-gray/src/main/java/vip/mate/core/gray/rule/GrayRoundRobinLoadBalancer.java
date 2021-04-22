package vip.mate.core.gray.rule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.cloud.nacos.NacosServiceInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.RoundRobinLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.gray.context.VersionContextHolder;

import java.util.List;
import java.util.Map;

/**
 * 定义灰度负载均衡策略
 *
 * @author madi
 */
@Slf4j
public class GrayRoundRobinLoadBalancer extends RoundRobinLoadBalancer {

	private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

	private String serviceId;

	public GrayRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
		super(serviceInstanceListSupplierProvider, serviceId);
		this.serviceId = serviceId;
		this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
	}

	public GrayRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId, int seedPosition) {
		super(serviceInstanceListSupplierProvider, serviceId, seedPosition);
	}

	@Override
	public Mono<Response<ServiceInstance>> choose(Request request) {
		ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
				.getIfAvailable(NoopServiceInstanceListSupplier::new);
		return supplier.get(request).next().map(serviceInstances -> getInstanceResponse(serviceInstances, request));
	}

	Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, Request request) {

		// 注册中心无可用实例 抛出异常
		if (CollUtil.isEmpty(instances)) {
			log.warn("No instance available {}", serviceId);
			return new EmptyResponse();
		}
		String reqVersion = VersionContextHolder.getVersion();

		if (StrUtil.isBlank(reqVersion)) {
			return super.choose(request).block();
		}

		// 遍历可以实例元数据，若匹配则返回此实例
		for (ServiceInstance instance : instances) {
			NacosServiceInstance nacosInstance = (NacosServiceInstance) instance;
			Map<String, String> metadata = nacosInstance.getMetadata();
			String targetVersion = MapUtil.getStr(metadata, MateConstant.VERSION);
			if (reqVersion.equalsIgnoreCase(targetVersion)) {
				log.debug("gray requst match success :{} {}", reqVersion, nacosInstance);
				return new DefaultResponse(nacosInstance);
			}
		}
		return super.choose(request).block();
	}
}