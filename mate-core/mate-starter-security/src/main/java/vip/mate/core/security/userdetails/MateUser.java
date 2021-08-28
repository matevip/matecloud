package vip.mate.core.security.userdetails;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 自定义用户
 *
 * @author pangu
 */
@Getter
public class MateUser extends User {

	private static final long serialVersionUID = -5768257947433986L;

	/**
	 * 用户ID
	 */
	private final Long id;

	/**
	 * 部门ID
	 */
	private final String roleId;
	/**
	 * 部门ID
	 */
	private final Long departId;

	/**
	 * 手机号
	 */
	private final String phone;

	/**
	 * 头像
	 */
	private final String avatar;

	/**
	 * 租户ID
	 */
	private final String tenantId;

	/**
	 * 登录类型
	 */
	private final int type;

	public MateUser(Long id, int type, Long departId, String roleId, String phone, String avatar, String tenantId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.type = type;
		this.roleId = roleId;
		this.departId = departId;
		this.phone = phone;
		this.avatar = avatar;
		this.tenantId = tenantId;
	}
}
