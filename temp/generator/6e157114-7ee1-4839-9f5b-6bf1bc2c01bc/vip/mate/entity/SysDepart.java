package vip.mate.entity;

import vip.mate.core.database.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织机构表
 * </p>
 *
 * @author pangu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_depart")
@ApiModel(value="SysDepart对象", description="组织机构表")
public class SysDepart extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识")
    private String isDeleted;

    @ApiModelProperty(value = "上级ID")
    private Long parentId;

    @ApiModelProperty(value = "租户ID")
    private Long tenantId;


}
