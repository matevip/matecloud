package vip.mate.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
public class LogResponseGlobalFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
        ServerRequest serverRequest = ServerRequest.create(exchange,
                HandlerStrategies.withDefaults().messageReaders());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    log.info("================ Gateway Response Start  ================");
                    ServerHttpResponse response = exchange.getResponse();
                    recordLog(exchange.getRequest(), response);
                }
        ));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 记录到响应日志中去
     * @param response response
     */
    private void recordLog(ServerHttpRequest request, ServerHttpResponse response) {
        // 记录要访问的url
        StringBuilder builder = new StringBuilder(" request url: ");
        builder.append(request.getURI().getRawPath());

        // 记录访问的方法
        HttpMethod method = request.getMethod();
        if (null != method){
            builder.append(", method: ").append(method.name());
        }
        // 记录头部信息
        builder.append(", header { ");
        for (Map.Entry<String, List<String>> entry : response.getHeaders().entrySet()) {
            builder.append(entry.getKey()).append(":").append(StringUtils.join(entry.getValue(), ",")).append(",");
        }

        // 记录参数
        builder.append("} param {");

        log.info(builder.toString());
        log.info("================  Gateway Response End  =================");
//        LogUtil.info(builder.toString());
    }
}
