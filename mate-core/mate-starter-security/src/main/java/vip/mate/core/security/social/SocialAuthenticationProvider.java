package vip.mate.core.security.social;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import vip.mate.core.security.sms.SmsCodeAuthenticationToken;
import vip.mate.core.security.userdetails.MateUserDetailsService;

import java.util.Objects;

@AllArgsConstructor
public class SocialAuthenticationProvider implements AuthenticationProvider {

    private final MateUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialAuthenticationToken authenticationToken = (SocialAuthenticationToken) authentication;

        /**
         * 调用 {@link UserDetailsService}
         */
        UserDetails user = userDetailsService.loadUserByMobile((String)authenticationToken.getPrincipal());

        if (Objects.isNull(user)) {
            throw new InternalAuthenticationServiceException("社交登录错误");
        }

        SocialAuthenticationToken authenticationResult = new SocialAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
