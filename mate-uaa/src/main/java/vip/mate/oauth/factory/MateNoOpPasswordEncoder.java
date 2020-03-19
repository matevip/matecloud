package vip.mate.oauth.factory;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MateNoOpPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    /**
     * Get the singleton {@link MateNoOpPasswordEncoder}.
     */
    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }

    private static final PasswordEncoder INSTANCE = new MateNoOpPasswordEncoder();

    private MateNoOpPasswordEncoder() {
    }
}
