package vip.mate.core.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode()
@Accessors(chain = true)
public class MateEntity implements Serializable {

    private static final long serialVersionUID = -6747032505753527871L;


//    @ApiModelProperty(value = "创建人", hidden = true)
//    private String createBy;
//
//    @ApiModelProperty(value = "更新人", hidden = true)
//    private String updatedBy;
//
//    @ApiModelProperty(value = "创建时间", hidden = true)
//    private Timestamp createTime;
//
//    @ApiModelProperty(value = "更新时间", hidden = true)
//    private Timestamp updateTime;


}
