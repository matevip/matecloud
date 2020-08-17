package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

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
public class SysUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 真名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String telephone;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 部门id
     */
    private Long departId;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标识
     */
    private String isDeleted;

    @TableField(exist = false)
    private String departName;
    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private String roleName;


}
