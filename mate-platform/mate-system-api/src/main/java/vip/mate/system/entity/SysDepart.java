package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

import java.util.List;

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
@ApiModel(value = "SysDepart对象", description = "组织机构表")
public class SysDepart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private String isDeleted;
    /**
     * 上级ID
     */
    @ApiModelProperty(value = "上级ID")
    private Long parentId;
    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

}
