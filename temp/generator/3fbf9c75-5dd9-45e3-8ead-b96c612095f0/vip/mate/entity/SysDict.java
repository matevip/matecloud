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
 * 字典表
 * </p>
 *
 * @author pangu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_dict")
@ApiModel(value="SysDict对象", description="字典表")
public class SysDict extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "父主键")
    private Long parentId;

    @ApiModelProperty(value = "字典码")
    private String code;

    @ApiModelProperty(value = "字典值")
    private String dictKey;

    @ApiModelProperty(value = "字典名称")
    private String dictValue;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "字典备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;


}
