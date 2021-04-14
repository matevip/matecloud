package vip.mate.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.gateway.config.GatewayLoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.DelegatingServiceInstance;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vip.mate.gateway.rule.GrayLoadBalancer;

import java.net.URI;

/**
 * 灰度过滤器
 *
 * @author L.cm
 * @author madi
 * @date 2021-02-24 13:41
 */
@Slf4j
public class GrayReactiveLoadBalancerClientFilter extends ReactiveLoadBalancerClientFilter {
	private static final int LOAD_BALANCER_CLIENT_FILTER_ORDER = 10150;
	private GatewayLoadBalancerProperties properties;

	private GrayLoadBalancer grayLoadBalancer;

	public GrayReactiveLoadBalancerClientFilter(GatewayLoadBalancerProperties properties, GrayLoadBalancer grayLoadBalancer) {
		super(null, properties, null);
		this.properties = properties;
		this.grayLoadBalancer = grayLoadBalancer;
	}

	@Override
	public int getOrder() {
		return LOAD_BALANCER_CLIENT_FILTER_ORDER;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		URI url = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
		String schemePrefix = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR);
		//判断url 前缀 如不是lb开头的就进行下一个过滤器
		if (url == null
				|| (!"lb".equals(url.getScheme()) && !"lb".equals(schemePrefix))) {
			return chain.filter(exchange);
		}
		//根据网关的原始网址。替换exchange url为 http://IP:PORT/path 路径的url
		// preserve the original url
		ServerWebExchangeUtils.addOriginalRequestUrl(exchange, url);

		if (log.isTraceEnabled()) {
			log.trace(ReactiveLoadBalancerClientFilter.class.getSimpleName()
					+ " url before: " + url);
		}
		// 这里呢会进行调用真正的负载均衡
		return choose(exchange).doOnNext(response -> {

			if (!response.hasServer()) {
				throw NotFoundException.create(properties.isUse404(),
						"Unable to find instance for " + url.getHost());
			}

			URI uri = exchange.getRequest().getURI();

			// if the `lb:<scheme>` mechanism was used, use `<scheme>` as the default,
			// if the loadbalancer doesn't provide one.
			String overrideScheme = null;
			if (schemePrefix != null) {
				overrideScheme = url.getScheme();
			}

			DelegatingServiceInstance serviceInstance = new DelegatingServiceInstance(
					response.getServer(), overrideScheme);

			URI requestUrl = LoadBalancerUriTools.reconstructURI(serviceInstance, uri);

			if (log.isTraceEnabled()) {
				log.trace("LoadBalancerClientFilter url chosen: " + requestUrl);
			}
			exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, requestUrl);
		}).then(chain.filter(exchange));
	}

	private Mono<Response<ServiceInstance>> choose(ServerWebExchange exchange) {
		URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
		ServiceInstance serviceInstance = grayLoadBalancer.choose(uri.getHost(), exchange.getRequest());
		return Mono.just(new DefaultResponse(serviceInstance));
	}
}
