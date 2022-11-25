package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

import java.util.List;

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
@Schema(name = "SysRole对象", description = "角色表")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String roleName;
    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String roleCode;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private int sort;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;
    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    private String isDeleted;
    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Integer tenantId;

    /**
     * 菜单列表
     */
    @TableField(exist = false)
    @Schema(description = "菜单列表")
    private List<String> menu;
}
