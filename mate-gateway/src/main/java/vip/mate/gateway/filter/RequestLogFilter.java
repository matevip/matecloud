package vip.mate.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vip.mate.core.common.constant.MateConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印请求和响应简要日志
 * @author pangu
 * @since 2020-7-16
 */
@Slf4j
@Component
@AllArgsConstructor
public class RequestLogFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        String traceId = exchange.getRequest().getHeaders().getFirst(MateConstant.MATE_TRACE_ID);
        StringBuilder reqBuilder = new StringBuilder(300);
        List<Object> reqArgs = new ArrayList<>();
        reqBuilder.append("Request ===> Method:{} Host:{} Path:{} Query:{} TraceId:{}");
        reqArgs.add(exchange.getRequest().getMethodValue());
        reqArgs.add(exchange.getRequest().getURI().getHost());
        reqArgs.add(requestUrl);
        reqArgs.add(exchange.getRequest().getQueryParams());
        reqArgs.add(traceId);
        log.info(reqBuilder.toString(), reqArgs.toArray());

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then( Mono.fromRunnable(() -> {
            StringBuilder resBuilder = new StringBuilder(300);
            List<Object> resArgs = new ArrayList<>();
            ServerHttpResponse response = exchange.getResponse();
            Long startTime = exchange.getAttribute(START_TIME);
            long executeTime = 0L;
            if (startTime != null) {
                executeTime = (System.currentTimeMillis() - startTime);
            }
            resBuilder.append("Response <=== Status:{} Method:{} Path:{} Time:{}");
            String requestMethod = exchange.getRequest().getMethodValue();
            resArgs.add(response.getStatusCode().value());
            resArgs.add(requestMethod);
            resArgs.add(requestUrl);
            resArgs.add(executeTime + "ms");
            log.info(resBuilder.toString(), resArgs.toArray());
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
