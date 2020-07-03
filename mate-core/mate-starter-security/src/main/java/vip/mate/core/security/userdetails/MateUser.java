package vip.mate.core.security.userdetails;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MateUser extends User {

    private static final long serialVersionUID = -5768257947433986L;

    /**
     * 用户ID
     */
    @Getter
    private Long id;

    /**
     * 部门ID
     */
    @Getter
    private Long roleId;
    /**
     * 部门ID
     */
    @Getter
    private Long departId;

    /**
     * 手机号
     */
    @Getter
    private String phone;

    /**
     * 头像
     */
    @Getter
    private String avatar;

    /**
     * 租户ID
     */
    @Getter
    private String tenantId;

    public MateUser(Long id, Long departId, Long roleId, String phone, String avatar, String tenantId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.roleId = roleId;
        this.departId = departId;
        this.phone = phone;
        this.avatar = avatar;
        this.tenantId = tenantId;
    }
}
