package vip.mate.system.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import vip.mate.core.common.constant.MateConstant;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * POI对象
 *
 * @author pangu
 */
@Data
public class SysLogPOI implements Serializable {

	private static final long serialVersionUID = 6057481346126947055L;

	@Excel(name = "日志编号", orderNum = "0", width = 30, isImportField = "true_st")
	private Long id;

	@Excel(name = "TRACEID", orderNum = "0", width = 30, isImportField = "true_st")
	private String traceId;

	@Excel(name = "操作人", orderNum = "0", width = 30, isImportField = "true_st")
	private String createBy;

	@Excel(name = "操作", orderNum = "0", width = 30, isImportField = "true_st")
	private String title;

	@Excel(name = "执行方法", orderNum = "0", width = 30, isImportField = "true_st")
	private String method;

	@Excel(name = "请求路径", orderNum = "0", width = 30, isImportField = "true_st")
	private String url;

	@Excel(name = "IP地址", orderNum = "0", width = 30, isImportField = "true_st")
	private String ip;

	@Excel(name = "地址", orderNum = "0", width = 30, isImportField = "true_st")
	private String location;

	@Excel(name = "耗时", orderNum = "0", width = 30, isImportField = "true_st")
	private String executeTime;

	@Excel(name = "创建时间", format = MateConstant.DATETIME_FORMAT, orderNum = "5", width = 30, isImportField = "true_st")
	private LocalDateTime createTime;
}
