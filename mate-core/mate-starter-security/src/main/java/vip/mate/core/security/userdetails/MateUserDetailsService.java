package vip.mate.core.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 用户详细扩展
 * @author pangu
 */
public interface MateUserDetailsService extends UserDetailsService {

    /**
     * 根据手机号登录
     * @param mobile
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;

    /**
     * 根据社交账号登录
     * @param openId 第三方的绑定的openId
     * @return
     * @throws UsernameNotFoundException
     */
    UserDetails loadUserBySocial(String openId) throws UsernameNotFoundException;
}
