package vip.mate.core.security.social;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.enums.AuthResponseStatus;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;
import vip.mate.core.common.constant.Oauth2Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SocialAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static String SOCIAL_LOGIN_URL = "/auth/callback/**";

    /**
     * 请求中的参数
     */
    private String socialParameter = Oauth2Constant.DEFAULT_PARAMETER_NAME_SOCIAL;

    private AuthRequestFactory authRequestFactory;

    private boolean postOnly = true;

    /**
     * 通过构造函数指定该 Filter 要拦截的 url 和 httpMethod
     */
    protected SocialAuthenticationFilter() {
        super(new AntPathRequestMatcher(SOCIAL_LOGIN_URL, "GET"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 1. 从请求中获取参数 用户登录扩展参数
        String socialKey = obtainSocialKey(request);
        String socialCredentials = obtainCredentials(request);
        String socialType = obtainSocialType(request);

        // 2. 封装成 Token 调用 AuthenticationManager 的 authenticate 方法，该方法中根据 Token 的类型去调用对应 Provider 的 authenticated
        SocialAuthenticationToken token;
        if (StrUtil.isNotBlank(socialKey)) {
            token = new SocialAuthenticationToken(socialKey, socialType, socialCredentials);
        } else {
            // 从第三方拿到用户信息
            token = new SocialAuthenticationToken(obtainAuthUser(request));
        }
        this.setDetails(request, token);

        // 3. 返回 authenticated 方法的返回值
        return this.getAuthenticationManager().authenticate(token);


    }

    /**
     * 获取扩展登录extendKey，可以是用户名、手机号等，根据业务需要去扩展
     */
    protected String obtainSocialKey(HttpServletRequest request) {
        return request.getParameter(Oauth2Constant.SOCIAL_TYPE_PARAMETER);
    }

    /**
     * 获取扩展登录extendCredentials，可以是手机号的验证码等，根据业务需要去扩展
     */
    protected String obtainCredentials(HttpServletRequest request) {
        return request.getParameter(Oauth2Constant.SOCIAL_CREDENTIALS_PARAMETER);
    }

    /**
     * 获取扩展登录类型
     */
    protected String obtainSocialType(HttpServletRequest request) {
        return request.getParameter(Oauth2Constant.SOCIAL_TYPE_PARAMETER);
    }

    /**
     * 获取手机号
     */
    protected String obtainSocial(HttpServletRequest request) {
        return request.getParameter(socialParameter);
    }

    protected void setDetails(HttpServletRequest request, SocialAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setSocialParameter(String socialParameter) {
        Assert.hasText(socialParameter, "Social parameter must not be empty or null");
        this.socialParameter = socialParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getSocialParameter() {
        return socialParameter;
    }

    /**
     * 获取 justauth 登录后的用户信息
     */
    protected AuthUser obtainAuthUser(HttpServletRequest request) {
        String type = getCallbackType(request);
        AuthRequest authRequest = authRequestFactory.get(type);

        // 登录后，从第三方拿到用户信息
        AuthResponse response = authRequest.login(getCallback(request));
        log.info("【justauth 第三方登录 response】= {}", JSON.toJSON(response));
        // 第三方登录成功
        if (response.getCode() == AuthResponseStatus.SUCCESS.getCode()) {
            AuthUser authUser = (AuthUser) response.getData();
            return authUser;
        }
        return null;
    }

    /**
     * 从请求中构建 AuthCallback
     */
    private AuthCallback getCallback(HttpServletRequest request) {

        AuthCallback authCallback = AuthCallback.builder()
                .code(request.getParameter("code"))
                .auth_code(request.getParameter("auth_code"))
                .authorization_code(request.getParameter("authorization_code"))
                .oauth_token(request.getParameter("oauth_token"))
                .state(request.getParameter("state"))
                .oauth_verifier(request.getParameter("oauth_verifier"))
                .build();

        return authCallback;
    }


    /**
     * 获取路径参数：回调类型
     */
    private String getCallbackType(HttpServletRequest request) {
        // /context/open/oauth/callback/gitee
        String uri = request.getRequestURI();
        // "/open/oauth/callback/".length()
        int common = SOCIAL_LOGIN_URL.length() - 2;
        int start = uri.indexOf(SOCIAL_LOGIN_URL.substring(0, common));
        if(start == -1) {
            log.warn("【justauth 第三方登录 response】回调类型为空，uri={}", uri);
            return null;
        }
        // gitee
        return uri.substring(start + common);
    }

    public void setAuthRequestFactory(AuthRequestFactory authRequestFactory) {
        this.authRequestFactory = authRequestFactory;
    }

}
