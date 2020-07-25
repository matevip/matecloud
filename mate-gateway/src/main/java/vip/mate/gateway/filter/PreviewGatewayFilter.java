package vip.mate.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 演示环境过滤器
 * @author pangu
 * @since 2020-7-21
 */

@Slf4j
@Component
public class PreviewGatewayFilter extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // GET，直接向下执行 TODO 登录接口放行
            if (StringUtils.equalsIgnoreCase(request.getMethodValue(), HttpMethod.GET.name())) {
                return chain.filter(exchange);
            }

            log.warn("演示环境不能操作-> {},{}", request.getMethodValue(), request.getURI().getPath());
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.LOCKED);
            return response.setComplete();
        };
    }
}
