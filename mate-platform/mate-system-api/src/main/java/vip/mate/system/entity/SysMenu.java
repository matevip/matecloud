package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_menu")
@Schema(name = "SysMenu对象", description = "菜单权限表")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单标题
     */
    @Schema(description = "菜单标题")
    private String name;
    /**
     * 菜单权限
     */
    @Schema(description = "菜单权限")
    private String permission;
    /**
     * 路径
     */
    @Schema(description = "路径")
    private String path;
    /**
     * 组件
     */
    @Schema(description = "组件")
    private String component;
    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long parentId;
    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;
    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;
    /**
     * 是否缓存该页面: 1:是  0:不是
     */
    @Schema(description = "是否缓存该页面: 1:是  0:不是")
    private String keepAlive;
    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型")
    private String type;
    /**
     * 是否隐藏
     */
    @Schema(description = "是否隐藏")
    private String hidden;
    /**
     * 是否外链
     */
    @Schema(description = "是否外链")
    private String target;
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
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Long tenantId;

}
