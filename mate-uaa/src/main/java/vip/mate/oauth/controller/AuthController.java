package vip.mate.oauth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.oauth.service.CaptchaService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    public static String BEARER = "bearer";
    public static Integer AUTH_LENGTH = 7;

    @Qualifier("consumerTokenServices")
    private final ConsumerTokenServices consumerTokenServices;

    private final CaptchaService captchaService;

    @GetMapping("/auth/userInfo")
    public Result<Authentication> currentUser(Authentication authentication) {
        return Result.data(authentication);
    }

    @GetMapping("/auth/code")
    public Result<?> authCode() {
        return captchaService.getCode();
    }

    @PostMapping("/auth/logout")
    public Result<?> logout(HttpServletRequest request) {
        String headerToken = request.getHeader("Mate-Auth");
        boolean revokeToken = consumerTokenServices.revokeToken(getToken(headerToken));
        return Result.success("操作成功");
    }

    /**
     * 获取token串
     *
     * @param auth token
     * @return String
     */
    public static String getToken(String auth) {
        if ((auth != null) && (auth.length() > AUTH_LENGTH)) {
            String headStr = auth.substring(0, 6).toLowerCase();
            if (headStr.compareTo(BEARER) == 0) {
                auth = auth.substring(7);
            }
            return auth;
        }
        return null;
    }
}
