package vip.mate.core.database.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索封装类
 */
@Data
@ApiModel(description = "搜索条件")
public class Search implements Serializable {

    @ApiModelProperty(value = "关键词")
    private String keyword;

    @ApiModelProperty(value = "开始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    private String endDate;

    @ApiModelProperty(value = "排序属性")
    private String prop;

    @ApiModelProperty(value = "排序方式：asc,desc")
    private String order;
}
