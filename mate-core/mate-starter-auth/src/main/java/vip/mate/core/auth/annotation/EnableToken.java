package vip.mate.core.auth.annotation;

import java.lang.annotation.*;

/**
 * 启用Token验证
 *
 * @author pangu
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableToken {
}
