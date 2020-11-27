package vip.mate.core.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 类操作工具类
 *
 * @author pangu
 */
@UtilityClass
public class ClassUtil extends ClassUtils {

	/**
	 * 获取Annotation
	 *
	 * @param method         Method
	 * @param annotationType 注解类
	 * @param <A>            泛型标记
	 * @return {Annotation}
	 */
	@Nullable
	public static <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
		Class<?> targetClass = method.getDeclaringClass();
		// The method may be on an interface, but we need attributes from the target class.
		// If the target class is null, the method will be unchanged.
		Method specificMethod = ClassUtil.getMostSpecificMethod(method, targetClass);
		// If we are dealing with method with generic parameters, find the original method.
		specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
		// 先找方法，再找方法上的类
		A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);
		if (null != annotation) {
			return annotation;
		}
		// 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
		return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
	}

	/**
	 * 判断是否有注解 Annotation
	 *
	 * @param method         Method
	 * @param annotationType 注解类
	 * @param <A>            泛型标记
	 * @return {boolean}
	 */
	public static <A extends Annotation> boolean isAnnotated(Method method, Class<A> annotationType) {
		// 先找方法，再找方法上的类
		boolean isMethodAnnotated = AnnotatedElementUtils.isAnnotated(method, annotationType);
		if (isMethodAnnotated) {
			return true;
		}
		// 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
		Class<?> targetClass = method.getDeclaringClass();
		return AnnotatedElementUtils.isAnnotated(targetClass, annotationType);
	}

}
