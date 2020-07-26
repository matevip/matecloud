package vip.mate.core.security.social;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SocialAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = -3629824093049247125L;

    // ~ Instance fields
    // ================================================================================================

    private String socialType;
    private String socialKey;
    private final Object principal;
    private Object credentials;

    // ~ Constructors
    // ===================================================================================================
    /**
     * 认证前使用
     */
    public SocialAuthenticationToken(String socialKey, String socialType, Object credentials) {
        super(null);
        this.socialKey = socialKey;
        this.socialType = socialType;
        this.principal = socialKey;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * justauth 使用
     */
    public SocialAuthenticationToken(Object authUser) {
        super(null);
        this.principal = authUser;
        setAuthenticated(false);
    }

    /**
     * 认证成功之后使用
     */
    public SocialAuthenticationToken(String socialKey, String socialType, Object principal,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.socialKey = socialKey;
        this.socialType = socialType;
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    public SocialAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    // ~ Methods
    // ========================================================================================================


    @Override
    public Object getCredentials() {
        return this.principal;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    public String getSocialType() {
        return socialType;
    }

    public String getSocialKey() {
        return socialKey;
    }


    @Override
    @SneakyThrows
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
