package vip.mate.gateway.filter;

import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.util.SqlInjectionUtil;
import vip.mate.gateway.util.WebFluxUtil;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * SQL 注入拦截过滤器
 *
 * @author : hackerdom
 */
@Slf4j
@Component
public class SqlInjectionFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);

        URI uri = exchange.getRequest().getURI();

        if (isGetRequest(method)) {
            String rawQuery = uri.getRawQuery();
            if (StringUtils.isBlank(rawQuery)) {
                return chain.filter(exchange);
            }
            log.debug("sql injection filter request parameter is [{}]", rawQuery);
            // 执行XSS清理
            boolean isSQLInjection = SqlInjectionUtil.checkForGet(rawQuery);

            // 如果存在sql注入,直接拦截请求
            if (isSQLInjection) {
                return sqlInjectionResponse(exchange, uri);
            }
            // 不对参数做任何处理
            return chain.filter(exchange);
        }

        if (isPostRequest(method, contentType)) {
            String bodyString = resolveBodyFromRequest(serverHttpRequest);

            if (StringUtils.isNotBlank(bodyString)) {

                boolean isSQLInjection;
                if (WebFluxUtil.isJsonMediaType(contentType)) {
                    // 如果MediaType是json才执行json方式验证
                    isSQLInjection = SqlInjectionUtil.checkForPost(bodyString);
                } else {
                    // form表单方式，需要走get请求
                    isSQLInjection = SqlInjectionUtil.checkForGet(bodyString);
                }

                //  如果存在sql注入,直接拦截请求
                if (isSQLInjection) {
                    return sqlInjectionResponse(exchange, uri);
                }

                ServerHttpRequest request = serverHttpRequest.mutate().uri(uri).build();
                // 重新构造body
                byte[] newBytes = bodyString.getBytes(StandardCharsets.UTF_8);
                DataBuffer bodyDataBuffer = toDataBuffer(newBytes);
                Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

                HttpHeaders headers = new HttpHeaders();
                headers.putAll(exchange.getRequest().getHeaders());

                // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                int length = bodyString.getBytes().length;
                headers.remove(HttpHeaders.CONTENT_LENGTH);
                headers.setContentLength(length);

                // 设置CONTENT_TYPE
                if (StringUtils.isNotBlank(contentType)) {
                    headers.set(HttpHeaders.CONTENT_TYPE, contentType);
                }

                // 由于post的body只能订阅一次，由于上面代码中已经订阅过一次body。所以要再次封装请求到request才行，不然会报错请求已经订阅过
                request = new ServerHttpRequestDecorator(request) {
                    @Override
                    public HttpHeaders getHeaders() {
                        return headers;
                    }

                    @Override
                    public Flux<DataBuffer> getBody() {
                        return bodyFlux;
                    }
                };
                return chain.filter(exchange.mutate().request(request).build());
            }
        }

        return chain.filter(exchange);
    }

    /**
     * 从Flux<DataBuffer>中获取字符串的方法
     *
     * @return 请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }

    private Mono<Void> sqlInjectionResponse(ServerWebExchange exchange, URI uri) {
        log.error("paramters of request [" + uri.getRawPath() + uri.getRawQuery() + "] contain illegal sql keyword!");
        return WebFluxUtil.writeJsonResponse(exchange.getResponse(), Result.fail(403, "疑似SQL注入请求"));
    }

    private boolean isGetRequest(HttpMethod method) {
        return method == HttpMethod.GET;
    }

    private Boolean isPostRequest(HttpMethod method, String contentType) {
        return (method == HttpMethod.POST || method == HttpMethod.PUT) && (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType) || MediaType.APPLICATION_JSON_VALUE.equals(contentType));
    }

    /**
     * 自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
