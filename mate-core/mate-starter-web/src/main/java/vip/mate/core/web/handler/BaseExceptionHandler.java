package vip.mate.core.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.exception.TokenException;

import java.io.FileNotFoundException;

/**
 * Springboot WEB应用全局异常处理
 * @author pangu
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    /**
     * TokenException 异常捕获处理
     * @param ex 自定义TokenException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<?> handleException(TokenException ex) {
        log.error("程序异常：" + ex.toString());
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    /**
     * FileNotFoundException 异常捕获处理
     * @param ex 自定义FileNotFoundException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> handleException(FileNotFoundException ex) {
        log.error("程序异常：" + ex.toString());
        return Result.fail(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    /**
     * 通用Exception异常捕获
     * @param ex 自定义Exception异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleException(Exception ex) {
        log.error("程序异常：" + ex.toString());
        String message = ex.getMessage();
        if (StringUtils.contains(message, "Bad credentials")){
            message = "您输入的密码不正确";
        }
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    /**
     * NullPointerException 空指针异常捕获处理
     * @param ex 自定义NullPointerException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleException(NullPointerException ex) {
        log.error("程序异常：" + ex.toString());
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
