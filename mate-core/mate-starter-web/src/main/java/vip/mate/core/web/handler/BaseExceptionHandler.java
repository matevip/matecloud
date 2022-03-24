package vip.mate.core.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.WebConstant;
import vip.mate.core.common.exception.BaseException;
import vip.mate.core.common.exception.PreviewException;
import vip.mate.core.common.exception.TokenException;

import java.io.FileNotFoundException;

/**
 * Springboot WEB应用全局异常处理
 *
 * @author pangu
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * BaseException 异常捕获处理
     *
     * @param ex 自定义BaseException异常类型
     * @return Result
     */
    @ExceptionHandler(BaseException.class)
    public Result<?> handleBaseException(BaseException ex) {
        log.error("程序异常：" + ex.toString());
        return Result.fail(WebConstant.Status.UNAUTHORIZED.getCode(), ex.getMessage());
    }

    /**
     * TokenException 异常捕获处理
     *
     * @param ex 自定义TokenException异常类型
     * @return Result
     */
    @ExceptionHandler(TokenException.class)
    public Result<?> handleTokenException(TokenException ex) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return Result.fail(WebConstant.Status.UNAUTHORIZED.getCode(), ex.getMessage());
    }

    /**
     * FileNotFoundException,NoHandlerFoundException 异常捕获处理
     *
     * @param exception 自定义FileNotFoundException异常类型
     * @return Result
     */
    @ExceptionHandler({FileNotFoundException.class, NoHandlerFoundException.class})
    public Result<?> noFoundException(Exception exception) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return Result.fail(WebConstant.Status.NOT_FOUND.getCode(), exception.getMessage());
    }

    /**
     * PreviewException 空指针异常捕获处理
     *
     * @param ex 自定义PreviewException异常类型
     * @return Result
     */
    @ExceptionHandler(PreviewException.class)
    public Result<?> handlePreviewException(PreviewException ex) {
        log.error("程序异常：" + ex.toString());
        return Result.fail(WebConstant.Status.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }

    /**
     * NullPointerException 空指针异常捕获处理
     *
     * @param ex 自定义NullPointerException异常类型
     * @return Result
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<?> handleNullPointerException(NullPointerException ex) {
        log.error("程序异常：{}" + ex.toString());
        return Result.fail(WebConstant.Status.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage());
    }

    /**
     * 通用Exception异常捕获
     *
     * @param ex 自定义Exception异常类型
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception ex) {
        log.error("程序异常：" + ex.toString());
        String message = ex.getMessage();
        if (StringUtils.contains(message, "Bad credentials")) {
            message = "您输入的密码不正确";
        } else if (StringUtils.contains(ex.toString(), "InternalAuthenticationServiceException")) {
            message = "您输入的用户名不存在";
        }
        return Result.fail(WebConstant.Status.INTERNAL_SERVER_ERROR.getCode(), message);
    }
}
