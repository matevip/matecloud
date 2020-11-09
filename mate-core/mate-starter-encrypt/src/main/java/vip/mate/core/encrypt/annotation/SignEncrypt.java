package vip.mate.core.encrypt.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 验证签名注解
 *
 * @author gaoyang
 * @author pangu
 * @link https://github.com/gaoyang5323/Encrypt-Thanos
 * @date 2020-11-6
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignEncrypt {

	long timeout() default 60000L;

	TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
