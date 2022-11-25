package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

/**
 * <p>
 * 组织机构表
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_depart")
@Schema(name = "SysDepart对象", description = "组织机构表")
public class SysDepart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String name;
    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;
    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    private String isDeleted;
    /**
     * 上级ID
     */
    @Schema(description = "上级ID")
    private Long parentId;
    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    private Long tenantId;

}
