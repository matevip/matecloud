package vip.mate.core.encrypt.annotation;

import org.springframework.context.annotation.Import;
import vip.mate.core.encrypt.config.EncryptConfiguration;
import vip.mate.core.encrypt.config.WebConfiguration;

import java.lang.annotation.*;

/**
 * Enable encrypt of the Spring Application Context
 * 支持res和rsa的加密方式
 *
 * @author pangu
 * @since 1.5.8
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({EncryptConfiguration.class, WebConfiguration.class})
public @interface EnableEncrypt {
}
