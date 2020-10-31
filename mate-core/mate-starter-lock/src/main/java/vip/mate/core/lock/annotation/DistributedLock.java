package vip.mate.core.lock.annotation;

import java.lang.annotation.*;

/**
 * Redisson分布式锁注解
 *
 * @author pangu
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DistributedLock {

	/**
	 * 分布式锁名称
	 *
	 * @return String
	 */
	String value() default "distributed-lock-redisson";

	/**
	 * 锁超时时间,默认十秒
	 *
	 * @return int
	 */
	int expireSeconds() default 10;
}
