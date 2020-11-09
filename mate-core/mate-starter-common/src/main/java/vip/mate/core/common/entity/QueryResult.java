package vip.mate.core.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询对象
 *
 * @author pangu
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "查询条件")
public class QueryResult {

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Integer current;

	/**
	 * 每页的数量
	 */
	@ApiModelProperty(value = "每页的数量")
	private Integer size;

	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段")
	private String field;
	/**
	 * 排序规则，asc升序，desc降序
	 */
	@ApiModelProperty(value = "排序规则")
	private String order;
}
