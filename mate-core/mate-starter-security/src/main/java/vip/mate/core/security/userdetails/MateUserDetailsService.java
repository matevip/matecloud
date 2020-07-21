package vip.mate.core.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MateUserDetailsService extends UserDetailsService {

    /**
     * 根据社交登录code 登录
     * @param code TYPE@CODE
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobile(String code) throws UsernameNotFoundException;
}
