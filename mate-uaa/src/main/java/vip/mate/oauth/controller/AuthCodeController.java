package vip.mate.oauth.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthCodeController {

    private final StringRedisTemplate stringRedisTemplate;

    @GetMapping("/auth/code")
    public Result<?> authCode() {
        Map<String, String> data = new HashMap<String, String>();
        String uuid = UUID.randomUUID().toString().replace("-","");
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 32);
        captcha.getArithmeticString();// 获取运算的公式：3+2=?
        log.info("运算公式：" + captcha.getArithmeticString());
        String text = captcha.text();// 获取运算的结果：5
        log.info("运算结果：" + text);
        stringRedisTemplate.opsForValue().set("mate:auth:code:" + uuid, text);
        data.put("key", uuid);
        data.put("codeUrl", captcha.toBase64());
        return Result.data(data);
    }
}
