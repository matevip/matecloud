package vip.mate.core.web.annotation;

import java.lang.annotation.*;

/**
 * 是否启用自动获取用户信息注解
 * 样例：public Object getUser(@EnableUser LoginUser user)
 * @author pangu
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableUser {

    /**
     * 是否查询LoginUser对象所有信息，true则通过rpc接口查询
     */
    boolean value() default false;
}
