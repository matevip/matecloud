package vip.mate.core.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验手机号码格式
 *
 * @author aaronuu
 */
@Documented
@Constraint(validatedBy = PhoneValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneValue {

	String message() default "手机号码格式不正确";

	Class[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 是否必填
	 * <p>
	 * 如果必填，在校验的时候本字段没值就会报错
	 */
	boolean required() default true;

	@Target({ElementType.FIELD, ElementType.PARAMETER})
	@Retention(RUNTIME)
	@Documented
	@interface List {
		PhoneValue[] value();
	}
}
