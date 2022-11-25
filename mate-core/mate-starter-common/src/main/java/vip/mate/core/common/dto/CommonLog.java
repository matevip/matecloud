package vip.mate.core.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 日志对象
 *
 * @author xuzhanfu
 */
@Data
@Accessors(chain = true)
@Schema(name = "CommonLog对象", description = "普通日志封装")
public class CommonLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 日志类型
	 */
	@Schema(description = "日志类型")
	private String type;
	/**
	 * 跟踪ID
	 */
	@Schema(description = "跟踪ID")
	private String traceId;
	/**
	 * 日志标题
	 */
	@Schema(description = "日志标题")
	private String title;
	/**
	 * 操作内容
	 */
	@Schema(description = "操作内容")
	private String operation;
	/**
	 * 执行方法
	 */
	@Schema(description = "执行方法")
	private String method;

	/**
	 * 请求路径
	 */
	@Schema(description = "请求路径")
	private String url;
	/**
	 * 参数
	 */
	@Schema(description = "参数")
	private String params;
	/**
	 * ip地址
	 */
	@Schema(description = "ip地址")
	private String ip;
	/**
	 * 耗时
	 */
	@Schema(description = "耗时")
	private Long executeTime;
	/**
	 * 地区
	 */
	@Schema(description = "地区")
	private String location;
	/**
	 * 创建人
	 */
	@Schema(description = "创建人")
	private String createBy;
	/**
	 * 更新人
	 */
	@Schema(description = "更新人")
	private String updateBy;
	/**
	 * 创建时间
	 */
	@Schema(description = "创建时间")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@Schema(description = "更新时间")
	private LocalDateTime updateTime;
	/**
	 * 删除标识
	 */
	@Schema(description = "删除标识")
	private String isDeleted;
	/**
	 * 租户ID
	 */
	@Schema(description = "租户ID")
	private Integer tenantId;
	/**
	 * 异常信息
	 */
	@Schema(description = "异常信息")
	private String exception;

}
