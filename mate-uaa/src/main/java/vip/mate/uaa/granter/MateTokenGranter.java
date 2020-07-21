package vip.mate.uaa.granter;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MateTokenGranter {

    /**
     * 自定义tokenGranter
     */
    public static TokenGranter getTokenGranter(final AuthenticationManager authenticationManager, final AuthorizationServerEndpointsConfigurer endpoints, StringRedisTemplate stringRedisTemplate) {
        // 默认tokenGranter集合
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        // 增加验证码模式
        granters.add(new SmsCodeTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), stringRedisTemplate));
        // 组合tokenGranter集合
        return new CompositeTokenGranter(granters);
    }
}
