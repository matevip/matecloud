package vip.mate.core.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验标识，只有Y和N两种状态的标识
 *
 * @author aaronuu
 */
@Documented
@Constraint(validatedBy = FlagValueValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FlagValue {

  String message() default "不正确的flag标识，请传递Y或者N";

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
  @Documented @interface List {
    FlagValue[] value();
  }
}
