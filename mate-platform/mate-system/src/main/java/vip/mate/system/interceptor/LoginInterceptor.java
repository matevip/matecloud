package vip.mate.system.interceptor;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vip.mate.core.auth.service.TokenService;
import vip.mate.core.common.context.UserContext;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.web.util.CollectionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@AllArgsConstructor
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            LoginUser loginUser = getUser(request, response);
            UserContext.setUser(loginUser);
        }
        return super.preHandle(request, response, handler);
    }

    private LoginUser getUser(HttpServletRequest request, HttpServletResponse response) {
        Claims claims = tokenService.checkToken(request);
        // 然后根据token获取用户登录信息，这里省略获取用户信息的过程
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(String.valueOf(claims.get("userId")));
        loginUser.setAccount((String) claims.get("userName"));
        loginUser.setRoleId(String.valueOf(claims.get("roleId")));
        return loginUser;
    }

}
