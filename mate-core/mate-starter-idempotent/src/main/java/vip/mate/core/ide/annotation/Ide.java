package vip.mate.core.ide.annotation;

import vip.mate.core.ide.enums.IdeTypeEnum;

import java.lang.annotation.*;

/**
 * @author pangu
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ide {

	/**
	 * 关键key
	 * key是本次请求中参数的键，
	 * 重复请求的key取自header中的rid
	 * 用来标识这个请求的唯一性
	 * 拦截器中会使用key从请求参数中获取value
	 *
	 * @return String
	 */
	String key() default "";

	/**
	 * 自定义key的前缀用来区分业务
	 */
	String perFix();

	/**
	 * 禁止重复提交的模式
	 * 默认是全部使用
	 */
	IdeTypeEnum ideTypeEnum() default IdeTypeEnum.ALL;
}
