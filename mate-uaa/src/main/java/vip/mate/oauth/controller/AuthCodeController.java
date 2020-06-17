package vip.mate.oauth.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.oauth.service.CaptchaService;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthCodeController {

    private final CaptchaService captchaService;

    @GetMapping("/auth/code")
    public Result<?> authCode() {
        return captchaService.getCode();
    }
}
