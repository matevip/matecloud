package vip.mate.core.auth.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import vip.mate.core.common.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * token验证切面
 *
 * @author xuzhanfu
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class TokenAspect {

	private final HttpServletRequest request;

	/**
	 * 定义切入点，切入点为标注自定义注解TokenAnnotation的方法
	 * 通过@Pointcut注解声明频繁使用的切点表达式
	 */
	@Pointcut("@annotation(vip.mate.core.auth.annotation.EnableToken)")
	public void tokenAspectPointcut() {

	}

	/**
	 * 定义切入点，切入点为标注自定义注解TokenAnnotation的方法
	 * 通过@Pointcut注解声明频繁使用的切点表达式
	 */
	@Before("tokenAspectPointcut()")
	public void before() {
		SecurityUtil.getClaims(SecurityUtil.getToken(request));
	}
}
