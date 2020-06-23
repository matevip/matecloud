package vip.mate.core.auth.advice;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.AuthException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;

@Slf4j
@Order(1)
@ControllerAdvice
@AllArgsConstructor
public class TokenRequestBodyAdvice implements RequestBodyAdvice {

    private final HttpServletRequest request;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
//        return ClassUtil.isAnnotated(methodParameter.getMethod(), EnableToken.class);
        return true;
    }

    @SneakyThrows
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        String headerToken = request.getHeader(Oauth2Constant.HEADER_TOKEN);
        if (StringUtils.isBlank(headerToken)) {
            throw new AuthException("Header中的token为空");
        }
        String token = StringUtils.isNotBlank(headerToken) ? TokenUtil.getToken(headerToken) : "";
        if (StringUtils.isNotBlank(token)) {
            Claims claims = TokenUtil.getClaims(token);
            if (claims == null) {
                throw new AuthException("Token已失效,请重新登录");
            }
        }
        return httpInputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return body;
    }
}
