package vip.mate.core.auth.annotation;

import java.lang.annotation.*;

/**
 * URL权限注解
 *
 * @author pangu
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuth {

	boolean enabled() default true;

	String value() default "permit()";
}
