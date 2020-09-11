package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_role")
@ApiModel(value = "SysRole对象", description = "角色表")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Integer tenantId;
}
