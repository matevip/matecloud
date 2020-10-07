package vip.mate.core.retrofit.annotation;

import com.github.lianjiatech.retrofit.spring.boot.annotation.InterceptMark;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import vip.mate.core.retrofit.interceptor.SignInterceptor;

import java.lang.annotation.*;

/**
 * 签名注解
 *
 * @author pangu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@InterceptMark
public @interface Sign {

	/**
	 * secret key
	 * Support the configuration in the form of placeholder.
	 *
	 * @return
	 */
	String accessKeyId();

	/**
	 * secret key
	 * Support the configuration in the form of placeholder.
	 *
	 * @return
	 */
	String accessKeySecret();

	/**
	 * Interceptor matching path.
	 *
	 * @return
	 */
	String[] include() default {"/**"};

	/**
	 * Interceptor excludes matching and intercepting by specified path.
	 *
	 * @return
	 */
	String[] exclude() default {};

	/**
	 * The interceptor class which handles the annotation.
	 * Get the corresponding bean from the spring container firstly.If not, use
	 * reflection to create one!
	 *
	 * @return
	 */
	Class<? extends BasePathMatchInterceptor> handler() default SignInterceptor.class;
}
