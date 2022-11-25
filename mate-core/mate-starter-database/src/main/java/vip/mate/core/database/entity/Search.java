package vip.mate.core.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索封装类
 *
 * @author pangu
 */
@Data
@Schema(description = "搜索条件")
public class Search implements Serializable {

	/**
	 * 关键词
	 */
	@Schema(description = "关键词")
	private String keyword;

	/**
	 * 开始日期
	 */
	@Schema(description = "开始日期")
	private String startDate;

	/**
	 * 结束日期
	 */
	@Schema(description = "结束日期")
	private String endDate;

	/**
	 * 排序属性
	 */
	@Schema(description = "排序属性")
	private String prop;

	/**
	 * 排序方式：asc,desc
	 */
	@Schema(description = "排序方式")
	private String order;

	/**
	 * 当前页
	 */
	@Schema(description = "当前页")
	private Integer current = 1;

	/**
	 * 每页的数量
	 */
	@Schema(description = "每页的数量")
	private Integer size = 10;
}
