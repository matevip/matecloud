package vip.mate.oauth.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.mate.core.security.userdetails.MateUser;
import vip.mate.system.entity.SysUser;
import vip.mate.system.feign.ISysUserProvider;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private ISysUserProvider sysUserProvider;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SysUser user = sysUserProvider.loadUserByUserName(userName).getData();
        log.info("用户名：{}", userName);
        return new MateUser(user.getId(), user.getDeptId(), user.getTelephone(), user.getAvatar(),
                user.getTenantId(), user.getAccount(), user.getPassword(), user.getStatus().equals("0")?true:false,
                true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin, ROLE_USER"));
    }
}
