package vip.mate.core.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录用户对象
 *
 * @author pangu
 */
@Data
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1599282604110237521L;

	/**
	 * 用户id
	 */
	@ApiModelProperty(hidden = true)
	private String userId;
	/**
	 * 账号
	 */
	@ApiModelProperty(hidden = true)
	private String account;
	/**
	 * 用户名
	 */
	@ApiModelProperty(hidden = true)
	private String userName;
	/**
	 * 昵称
	 */
	@ApiModelProperty(hidden = true)
	private String nickName;
	/**
	 * 租户ID
	 */
	@ApiModelProperty(hidden = true)
	private String tenantId;
	/**
	 * 部门id
	 */
	@ApiModelProperty(hidden = true)
	private String deptId;
	/**
	 * 岗位id
	 */
	@ApiModelProperty(hidden = true)
	private String postId;
	/**
	 * 角色id
	 */
	@ApiModelProperty(hidden = true)
	private String roleId;
	/**
	 * 角色名
	 */
	@ApiModelProperty(hidden = true)
	private String roleName;

	/**
	 * 登录类型
	 */
	@ApiModelProperty(hidden = true)
	private int type;

}
