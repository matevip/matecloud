package vip.mate.uaa.granter;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.security.sms.SmsCodeAuthenticationToken;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 手机号验证码登录TokenGranter
 * @author pangu
 * @since 2020-7-21
 */
public class SmsCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sms";

    private final AuthenticationManager authenticationManager;

    private StringRedisTemplate stringRedisTemplate;

    public SmsCodeTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory, StringRedisTemplate stringRedisTemplate) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    protected SmsCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String mobile = parameters.get("mobile");
        String code = parameters.get("code");

        String codeFromRedis = stringRedisTemplate.opsForValue().get(Oauth2Constant.SMS_CODE_KEY + mobile);

        if (StringUtils.isBlank(code)) {
            throw new UserDeniedAuthorizationException("请输入验证码");
        }
        if (codeFromRedis == null) {
            throw new UserDeniedAuthorizationException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(code, codeFromRedis)) {
            throw new UserDeniedAuthorizationException("验证码不正确");
        }

        stringRedisTemplate.delete(Oauth2Constant.CAPTCHA_KEY + mobile);


        Authentication userAuth = new SmsCodeAuthenticationToken(mobile);
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
            throw new InvalidGrantException("Could not authenticate user: " + mobile);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
