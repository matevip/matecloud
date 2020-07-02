package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import vip.mate.core.common.entity.MateEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class SysUser extends MateEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键 ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value = "主键")
    private Long id;

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
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    private String isDeleted;

    @TableField(exist = false)
    private String departName;
    @TableField(exist = false)
    private String statusName;


}
