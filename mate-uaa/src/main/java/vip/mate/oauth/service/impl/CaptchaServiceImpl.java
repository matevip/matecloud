package vip.mate.oauth.service.impl;

import com.wf.captcha.SpecCaptcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.CaptchaException;
import vip.mate.oauth.service.CaptchaService;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public Result<?> getCode() {

        Map<String, String> data = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-","");
        SpecCaptcha captcha = new SpecCaptcha(120, 40);
        String text = captcha.text();// 获取运算的结果：5
        stringRedisTemplate.opsForValue().set(Oauth2Constant.CAPTCHA_KEY + uuid, text, Duration.ofMinutes(30));
        data.put("key", uuid);
        data.put("codeUrl", captcha.toBase64());
        return Result.data(data);
    }

    @Override
    public void check(String key, String code) throws CaptchaException {
        String codeFormRedis = stringRedisTemplate.opsForValue().get(Oauth2Constant.CAPTCHA_KEY + key);

        if (StringUtils.isBlank(code)) {
            throw new CaptchaException("请输入验证码");
        }
        if (codeFormRedis == null) {
            throw new CaptchaException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, String.valueOf(codeFormRedis))) {
            throw new CaptchaException("验证码不正确");
        }

        stringRedisTemplate.delete(Oauth2Constant.CAPTCHA_KEY + key);
    }
}
