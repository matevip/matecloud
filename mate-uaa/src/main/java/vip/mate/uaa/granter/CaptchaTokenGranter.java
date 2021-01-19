package vip.mate.uaa.granter;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.util.HttpContextUtil;
import vip.mate.core.redis.core.RedisService;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 验证码的TokenGranter
 * @author pangu
 * @since 2020-7-21
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "captcha";

    private final AuthenticationManager authenticationManager;

    private RedisService redisService;

    public CaptchaTokenGranter(AuthenticationManager authenticationManager,
                               AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory, RedisService redisService) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.redisService = redisService;
    }

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();

        if (null == request) {
            throw new OAuth2Exception("请求参数不存在！");
        }
        // 增加验证码判断
        String key = request.getHeader(Oauth2Constant.CAPTCHA_HEADER_KEY);
        String code = request.getHeader(Oauth2Constant.CAPTCHA_HEADER_CODE);
        Object codeFromRedis = redisService.get(Oauth2Constant.CAPTCHA_KEY + key);

        if (StringUtils.isBlank(code)) {
            throw new UserDeniedAuthorizationException("请输入验证码");
        }
        if (codeFromRedis == null) {
            throw new UserDeniedAuthorizationException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, codeFromRedis.toString())) {
            throw new UserDeniedAuthorizationException("验证码不正确");
        }

        redisService.del(Oauth2Constant.CAPTCHA_KEY + key);

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String username = parameters.get("username");
        String password = parameters.get("password");
        // Protect from downstream leaks of password
        parameters.remove("password");

        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        }
        catch (AccountStatusException | BadCredentialsException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        }
        // If the username/password are wrong the spec says we should send 400/invalid grant

        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }

}
