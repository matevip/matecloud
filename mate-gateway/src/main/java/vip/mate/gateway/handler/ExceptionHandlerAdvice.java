package vip.mate.gateway.handler;

import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import vip.mate.common.api.ApiResult;
import vip.mate.common.api.ResultCode;


@Slf4j
@Component
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = {ResponseStatusException.class})
    public ApiResult handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        if (StringUtils.contains(ex.getMessage(), HttpStatus.NOT_FOUND.toString())){
            return ApiResult.fail(ResultCode.NOT_FOUND, ex.getMessage());
        } else {
            return ApiResult.fail(ResultCode.ERROR);
        }
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public ApiResult handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return ApiResult.fail(ResultCode.ERROR);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResult handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return ApiResult.fail(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return ApiResult.fail();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return ApiResult.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult handle(Throwable throwable) {
        ApiResult result = ApiResult.fail();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
