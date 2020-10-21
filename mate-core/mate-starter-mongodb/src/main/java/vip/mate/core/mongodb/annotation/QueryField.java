package vip.mate.core.mongodb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * type表示查询类似，默认为equals
 * attribute表示要查询的属性，默认为空串，如果为空则为字段名称
 * @link https://gitee.com/qwer.com/open-mongodb
 *
 * @author pangu
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryField {

	QueryType type() default QueryType.EQUALS;

	String attribute() default "";
}
