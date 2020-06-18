package vip.mate.core.auth.aspect;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import vip.mate.core.auth.annotation.EnableMethodToken;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.AuthException;
import vip.mate.core.common.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class TokenAspect {

    private final HttpServletRequest request;

    @Around("@annotation(vip.mate.core.auth.annotation.EnableMethodToken)")
    public Object around(ProceedingJoinPoint point) {
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Method method = methodSignature.getMethod();
        EnableMethodToken enableToken = method.getAnnotation(EnableMethodToken.class);

        if (enableToken != null) {
            String headerToken = request.getHeader(Oauth2Constant.HEADER_TOKEN);
            if (StringUtils.isBlank(headerToken)) {
                return Result.data(4001, null, "Token为空！");
            }
            String token = StringUtils.isNotBlank(headerToken) ? TokenUtil.getToken(headerToken) : "";
            if (StringUtils.isNotBlank(token)) {
                Claims claims = TokenUtil.getClaims(token);
                if (claims == null) {
                    return Result.data(4001, null, "Token已过期！");
                }
            }
        }
        try{
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
