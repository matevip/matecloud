package vip.mate.core.security.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.api.ResultCode;
import vip.mate.core.common.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败的回调
 * @author pangu
 */
@Slf4j
public class MateAuthenticationFailureHandler  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Result<?> result = null;
        String username = request.getParameter("username");
        if (exception instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期", username);
            result = Result.fail(ResultCode.USER_ACCOUNT_EXPIRED);

        } else if (exception instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", username);
            result = Result.fail(ResultCode.USER_PASSWORD_ERROR);

        } else if (exception instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期", username);
            result = Result.fail(ResultCode.USER_PASSWORD_EXPIRED);

        } else if (exception instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用", username);
            result = Result.fail(ResultCode.USER_DISABLED);

        } else if (exception instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定", username);
            result = Result.fail(ResultCode.USER_LOCKED);

        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", username));
            result = Result.fail(ResultCode.USER_LOGIN_FAIL);

        } else {
            // 其他错误
            log.error(String.format("[登录失败] - [%s]其他错误", username), exception);
            result = Result.fail(ResultCode.USER_LOGIN_FAIL);
        }
        ResponseUtil.responseWriter(response, "UTF-8", HttpStatus.UNAUTHORIZED.value(), result);

    }
}
