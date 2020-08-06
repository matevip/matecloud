package vip.mate.core.elasticsearch.annotation;

import java.lang.annotation.*;

/**
 * ES entity 标识ID的注解
 * @author pangu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ESId {
}
