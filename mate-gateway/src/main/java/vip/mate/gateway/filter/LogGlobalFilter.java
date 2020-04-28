package vip.mate.gateway.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.CachedBodyOutputMessage;
import org.springframework.cloud.gateway.support.BodyInserterContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@AllArgsConstructor
public class LogGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取数据类型
        log.info("================ Gateway Request Start  ================");
        MediaType mediaType = exchange.getRequest().getHeaders().getContentType();
        ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());

        // 如果是json格式，将body内容转化为object or map 都可
        if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
            Mono<Object> modifiedBody = serverRequest.bodyToMono(Object.class)
                    .flatMap(body -> {
                        recordLog(exchange.getRequest(), body);
                        return Mono.just(body);
                    });

            return getVoidMono(exchange, chain, Object.class, modifiedBody);
        }
        // 如果是表单请求
        else if (MediaType.APPLICATION_FORM_URLENCODED.isCompatibleWith(mediaType)) {
            Mono<String> modifiedBody = serverRequest.bodyToMono(String.class)
                    // .log("modify_request_mono", Level.INFO)
                    .flatMap(body -> {
                        recordLog(exchange.getRequest(), body);

                        return Mono.just(body);
                    });

            return getVoidMono(exchange, chain, String.class, modifiedBody);
        }
        // TODO 这里未来还可以限制一些格式


        // 无法兼容的请求，则不读取body
        recordLog(exchange.getRequest(), "");
        return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
    }

    /**
     * 优先级默认设置为最高
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    private Mono<Void> getVoidMono(ServerWebExchange exchange, GatewayFilterChain chain, Class outClass, Mono<?> modifiedBody) {
        BodyInserter bodyInserter = BodyInserters.fromPublisher(modifiedBody, outClass);
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getRequest().getHeaders());
        // the new content type will be computed by bodyInserter
        // and then set in the request decorator
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        CachedBodyOutputMessage outputMessage = new CachedBodyOutputMessage(exchange, headers);
        return bodyInserter.insert(outputMessage, new BodyInserterContext())
                // .log("modify_request", Level.INFO)
                .then(Mono.defer(() -> {
                    ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(
                            exchange.getRequest()) {
                        @Override
                        public HttpHeaders getHeaders() {
                            long contentLength = headers.getContentLength();
                            HttpHeaders httpHeaders = new HttpHeaders();
                            httpHeaders.putAll(super.getHeaders());
                            if (contentLength > 0) {
                                httpHeaders.setContentLength(contentLength);
                            } else {
                                // TODO: this causes a 'HTTP/1.1 411 Length Required' on httpbin.org
                                httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                            }
                            return httpHeaders;
                        }

                        @Override
                        public Flux<DataBuffer> getBody() {
                            return outputMessage.getBody();
                        }
                    };
                    return chain.filter(exchange.mutate().request(decorator).build());
                }));
    }

    /**
     * 记录到请求日志中去
     * @param request request
     * @param body 请求的body内容
     */
    private void recordLog(ServerHttpRequest request, Object body) {
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
        for (Map.Entry<String, List<String>> entry : request.getHeaders().entrySet()) {
            builder.append(entry.getKey()).append(":").append(StringUtils.join(entry.getValue(), ",")).append(",");
        }

        // 记录参数
        builder.append("} param {");
        if (null != method && HttpMethod.GET.matches(method.name())) {
            // 记录请求的参数信息 针对GET 请求
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
                builder.append(entry.getKey()).append("=").append(StringUtils.join(entry.getValue(), ",")).append(",");
            }
            builder.append("}");
        }
        else {
            builder.append(request.getURI().getQuery());
            builder.append("} body: ");
            // 从body中读取参数
            builder.append(body);
        }

        log.info(builder.toString());
        log.info("================  Gateway Request End  =================");
//        LogUtil.info(builder.toString());
    }
}


