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

//    @Override
//    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
//        // 这里其实可以根据异常类型进行定制化逻辑
//        Throwable error = super.getError(request);
//        int code = HttpStatus.INTERNAL_SERVER_ERROR.value();
//        if (error instanceof FileNotFoundException) {
//            code = HttpStatus.NOT_FOUND.value();
//        } else if (error instanceof ResponseStatusException
//                && StringUtils.contains(error.getMessage(), HttpStatus.NOT_FOUND.toString())) {
//            code = HttpStatus.NOT_FOUND.value();
//        } else if (error instanceof TokenException) {
//            code = HttpStatus.UNAUTHORIZED.value();
//        }
//        Map<String, Object> errorAttributes = new HashMap<>(8);
//        errorAttributes.put("message", error.getMessage());
//        errorAttributes.put("code", code);
//        errorAttributes.put("method", request.methodName());
//        errorAttributes.put("path", request.path());
//        return errorAttributes;
//    }

    @Override
    @SuppressWarnings("all")
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

//    @Override
//    protected int getHttpStatus(Map<String, Object> errorAttributes) {
//        return HttpStatus.INTERNAL_SERVER_ERROR.value();
//    }

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
