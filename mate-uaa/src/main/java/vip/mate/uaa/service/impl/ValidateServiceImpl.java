package vip.mate.uaa.service.impl;

import cn.hutool.core.util.StrUtil;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.CaptchaException;
import vip.mate.core.redis.core.RedisService;
import vip.mate.uaa.service.ValidateService;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 验证码业务类
 * @author pangu
 */
@Slf4j
@Service
@AllArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final RedisService redisService;

    @Override
    public Result<?> getCode() {
        Map<String, String> data = new HashMap<>(2);
        String uuid = UUID.randomUUID().toString().replace("-","");
        //SpecCaptcha captcha = new SpecCaptcha(120, 40);
        //String text = captcha.text();// 获取运算的结果：5
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        // 获取运算的结果：5
        String text = captcha.text();
        redisService.set(Oauth2Constant.CAPTCHA_KEY + uuid, text, Duration.ofMinutes(30));
        data.put("key", uuid);
        data.put("codeUrl", captcha.toBase64());
        return Result.data(data);
    }

    @Override
    public Result<?> getSmsCode(String mobile) {

        String code = "1188";
        redisService.set(Oauth2Constant.SMS_CODE_KEY + mobile, code, Duration.ofMinutes(5));
        return Result.success("发送成功");
    }

    @Override
    public void check(String key, String code) throws CaptchaException {
        Object codeFromRedis = redisService.get(Oauth2Constant.CAPTCHA_KEY + key);

        if (StrUtil.isBlank(code)) {
            throw new CaptchaException("请输入验证码");
        }
        if (codeFromRedis == null) {
            throw new CaptchaException("验证码已过期");
        }
        if (!StrUtil.equalsIgnoreCase(code, codeFromRedis.toString())) {
            throw new CaptchaException("验证码不正确");
        }

        redisService.del(Oauth2Constant.CAPTCHA_KEY + key);
    }
}
