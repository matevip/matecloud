package vip.mate.core.web.support;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import vip.mate.core.common.context.UserContext;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.web.annotation.EnableUser;
import vip.mate.core.web.service.TokenService;

import javax.servlet.http.HttpServletRequest;

/**
 * 通过header里的token获取用户信息
 * @author pangu
 * @link https://my.oschina.net/u/4149877/blog/3143391/print
 * @link https://blog.csdn.net/aiyaya_/article/details/79221733
 */
@Slf4j
@AllArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final TokenService tokenService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isHasEnableUserAnn = parameter.hasParameterAnnotation(EnableUser.class);
        boolean isHasLoginUserParameter = parameter.getParameterType().isAssignableFrom(LoginUser.class);
        return isHasEnableUserAnn && isHasLoginUserParameter;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        EnableUser user = methodParameter.getParameterAnnotation(EnableUser.class);
        boolean value= user.value();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        LoginUser loginUser = getUser(request);
        /**
         * 根据value状态获取更多用户信息，待实现
         */
        return loginUser;
    }

    private LoginUser getUser(HttpServletRequest request) {
        Claims claims = tokenService.checkToken(request);
        // 然后根据token获取用户登录信息，这里省略获取用户信息的过程
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(String.valueOf(claims.get("userId")));
        loginUser.setAccount((String) claims.get("userName"));
        loginUser.setRoleId(String.valueOf(claims.get("roleId")));
        UserContext.setUser(loginUser);
        return loginUser;
    }
}
