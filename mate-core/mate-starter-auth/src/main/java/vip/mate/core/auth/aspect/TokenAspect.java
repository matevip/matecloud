package vip.mate.core.auth.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import vip.mate.core.auth.service.TokenService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class TokenAspect {

    private final HttpServletRequest request;

    private final TokenService tokenService;

    /**
     * 定义切入点，切入点为标注自定义注解TokenAnnotation的方法
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("@annotation(vip.mate.core.auth.annotation.EnableToken)")
    public void TokenAspectPointcut(){

    }

    /**
     * 定义切入点，切入点为标注自定义注解TokenAnnotation的方法
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Before("TokenAspectPointcut()")
    public void before() throws Exception{
        tokenService.checkToken(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
    }
}
