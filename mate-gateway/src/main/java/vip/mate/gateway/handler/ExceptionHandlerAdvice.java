package vip.mate.gateway.handler;

import cn.hutool.core.util.StrUtil;
import io.netty.channel.ConnectTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.api.ResultCode;
import vip.mate.core.common.exception.TokenException;
import vip.mate.core.common.util.StringPool;


/**
 * 异常处理通知
 *
 * @author pangu
 */
@Slf4j
@Component
public class ExceptionHandlerAdvice {
    @ExceptionHandler(value = {ResponseStatusException.class})
    public Result<?> handle(ResponseStatusException ex) {
        log.error("response status exception:{}", ex.getMessage());
        if (StrUtil.contains(ex.getMessage(), HttpStatus.NOT_FOUND.toString())) {
            return Result.fail(ResultCode.NOT_FOUND, ex.getMessage());
        } else if (StrUtil.contains(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.toString())) {
            // 状态为503时，打印消息
            return Result.fail(ResultCode.SERVICE_UNAVAILABLE,
                    ResultCode.SERVICE_UNAVAILABLE.getMsg() + StringPool.COLON + ex.getMessage());
        } else {
            return Result.fail(ResultCode.ERROR);
        }
    }

    @ExceptionHandler(value = {ConnectTimeoutException.class})
    public Result<?> handle(ConnectTimeoutException ex) {
        log.error("connect timeout exception:{}", ex.getMessage());
        return Result.fail(ResultCode.ERROR);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> handle(NotFoundException ex) {
        log.error("not found exception:{}", ex.getMessage());
        return Result.fail(ResultCode.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(RuntimeException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.fail(ex.getMessage());
    }

    @ExceptionHandler(value = {TokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<?> handle(TokenException ex) {
        log.error("runtime exception:{}", ex.getMessage());
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(Exception ex) {
        log.error("exception:{}", ex.getMessage());
        return Result.fail();
    }

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handle(Throwable throwable) {
        Result result = Result.fail();
        if (throwable instanceof ResponseStatusException) {
            result = handle((ResponseStatusException) throwable);
        } else if (throwable instanceof ConnectTimeoutException) {
            result = handle((ConnectTimeoutException) throwable);
        } else if (throwable instanceof NotFoundException) {
            result = handle((NotFoundException) throwable);
        } else if (throwable instanceof TokenException) {
            result = handle((TokenException) throwable);
        } else if (throwable instanceof RuntimeException) {
            result = handle((RuntimeException) throwable);
        } else if (throwable instanceof Exception) {
            result = handle((Exception) throwable);
        }
        return result;
    }
}
