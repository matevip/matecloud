package vip.mate.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.props.MateRequestProperties;

import java.util.UUID;

/**
 * 给请求增加IP地址和TraceId
 * @author pangu
 * @since 2020-7-13
 */
@Slf4j
@Component
@AllArgsConstructor
public class PreRequestFilter implements GlobalFilter, Ordered {

    private final MateRequestProperties mateRequestProperties;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (mateRequestProperties.getIsTraceId()) {
            //链路追踪id
            String traceId = UUID.randomUUID().toString().replace("-","");
            log.error("traceId: {}", traceId);
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate()
                    .headers(h -> h.add(MateConstant.X_REQUEST_ID, traceId))
                    .build();

            ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
            return chain.filter(build);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
