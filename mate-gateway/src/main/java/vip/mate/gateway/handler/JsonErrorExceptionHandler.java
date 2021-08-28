package vip.mate.gateway.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 自定义的JsonErrorWebExceptionHandler异常处理类
 * @author xuzf
 * date: 2020-4-20
 * 参考文档：https://www.cnblogs.com/throwable/p/10848879.html
 */
@Slf4j
public class JsonErrorExceptionHandler extends DefaultErrorWebExceptionHandler {

    @Autowired
    private ExceptionHandlerAdvice exceptionHandlerAdvice;

    public JsonErrorExceptionHandler(ErrorAttributes errorAttributes,
                                     ResourceProperties resourceProperties,
                                     ErrorProperties errorProperties,
                                     ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    @SuppressWarnings("all")
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        Map<String, Object> error = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        int errorStatus = getHttpStatus(error);
        Throwable throwable = getError(request);
        return ServerResponse.status(errorStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(exceptionHandlerAdvice.handle(throwable)));
    }
}
