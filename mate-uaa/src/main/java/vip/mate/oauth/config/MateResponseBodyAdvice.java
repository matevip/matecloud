package vip.mate.oauth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.Oauth2Constant;

@Slf4j
@ControllerAdvice
public class MateResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //此处返回true,表示对任何handler的responsebody都调用beforeBodyWrite方法，如果有特殊方法不使用可以考虑使用注解等方式过滤
        RequestMatcher matcher = new AntPathRequestMatcher(Oauth2Constant.OAUTH_TOKEN, HttpMethod.POST.toString());
        log.error("getTypeName():{}", methodParameter.getGenericParameterType().getTypeName());
        if(methodParameter.getGenericParameterType().getTypeName().contains("Result")){
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("请求返回数据类型class="+ body.getClass().getName());
        if (body.toString().contains("error") && body.toString().contains("Unauthorized")){
            return Result.data(4001, body, "未鉴权");
        }else if (body.toString().contains("error")){
            return Result.data(5000, body, "密码不正确");
        }
        if (log.isDebugEnabled()) {
            log.debug("请求返回数据body=     " + body.toString());
        }
        return Result.data(body);
    }

}
