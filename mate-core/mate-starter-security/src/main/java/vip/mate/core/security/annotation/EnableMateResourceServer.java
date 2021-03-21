package vip.mate.core.security.annotation;

import org.springframework.context.annotation.Import;
import vip.mate.core.security.config.MateResourceServerConfig;

import java.lang.annotation.*;

/**
 * Security资源注解类，用于启动类
 *
 * @author pangu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MateResourceServerConfig.class)
public @interface EnableMateResourceServer {
}
