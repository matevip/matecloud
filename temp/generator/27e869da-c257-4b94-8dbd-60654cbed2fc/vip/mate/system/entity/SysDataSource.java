package vip.mate.system.entity;

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
 * 数据源表
 * </p>
 *
 * @author pangu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_data_source")
@ApiModel(value="SysDataSource对象", description="数据源表")
public class SysDataSource extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "驱动类")
    private String driverClass;

    @ApiModelProperty(value = "连接地址")
    private String url;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private Boolean status;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Boolean isDeleted;


}
