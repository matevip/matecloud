package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_user")
@Schema(name = "SysUser对象", description = "系统用户表")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 账号
     */
    @Schema(description = "账号")
    private String account;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String name;

    /**
     * 真名
     */
    @Schema(description = "真名")
    private String realName;

    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email;

    /**
     * 手机
     */
    @Schema(description = "手机")
    private String telephone;

    /**
     * 生日
     */
    @Schema(description = "生日")
    private LocalDateTime birthday;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Integer sex;

    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private String roleId;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long departId;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    private String isDeleted;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    @Schema(description = "部门名称")
    private String departName;

    /**
     * 状态名称
     */
    @TableField(exist = false)
    @Schema(description = "状态名称")
    private String statusName;

    /**
     * 权限名称
     */
    @TableField(exist = false)
    @Schema(description = "权限名称")
    private String roleName;


}
