package vip.mate.oauth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.mate.core.security.userdetails.MateUser;
import vip.mate.system.entity.User;
import vip.mate.system.feign.IUserProvider;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserProvider userProvider;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userProvider.loadUserByUserName(userName).getData();
        log.info("用户名：{}", userName);
        return new MateUser(user.getId(), user.getDeptId(), user.getTelephone(), user.getAvatar(),
                user.getTenantId(), user.getAccount(), user.getPassword(), user.getStatus(),
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_USER"));
    }
}
