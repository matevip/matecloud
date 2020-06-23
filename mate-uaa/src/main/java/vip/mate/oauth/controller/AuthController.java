package vip.mate.oauth.controller;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.security.util.SecurityUtil;
import vip.mate.core.security.util.TokenUtil;
import vip.mate.oauth.service.CaptchaService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    @Qualifier("consumerTokenServices")
    private final ConsumerTokenServices consumerTokenServices;

    private final CaptchaService captchaService;

    @GetMapping("/auth/userInfo")
    public Result<?> getUserInfo(HttpServletRequest request) {
        Claims claims = TokenUtil.getClaims(SecurityUtil.getToken(request));
        String userName = (String)claims.get("userName");
        String avatar = (String) claims.get("avatar");
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userName);
        data.put("avatar", avatar);
        return Result.data(data);
    }

    @GetMapping("/auth/code")
    public Result<?> authCode() {
        return captchaService.getCode();
    }

    @PostMapping("/auth/logout")
    public Result<?> logout(HttpServletRequest request) {
        consumerTokenServices.revokeToken(SecurityUtil.getToken(request));
        return Result.success("操作成功");
    }

}
