package vip.mate.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysClient;
import vip.mate.system.poi.SysClientPOI;
import vip.mate.system.service.ISysClientService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 客户端表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-07-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/client")
@Tag(name = "客户端管理")
public class SysClientController extends BaseController {

	private final ISysClientService sysClientService;

	/**
	 * 客户端分页
	 *
	 * @param search 　关键词
	 * @return Result
	 */
	@PreAuth
	@Log(value = "客户端分页", exception = "客户端分页请求异常")
	@GetMapping("/page")
	@Operation(summary = "客户端分页", description = "客户端分页")
	@Parameters({
			@Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
			@Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
			@Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
			@Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
			@Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
	})
	public Result<?> page(Search search) {
		return Result.data(sysClientService.listPage(search));
	}

	/**
	 * 客户端设置
	 *
	 * @param sysClient SysClient对象
	 * @return Result
	 */
	@PreAuth
	@Log(value = "客户端设置", exception = "客户端设置请求异常")
	@PostMapping("/set")
	@Operation(summary = "客户端设置", description = "客户端设置,支持新增或修改")
	public Result<?> set(@Valid @RequestBody SysClient sysClient) {
		return Result.condition(sysClientService.saveOrUpdate(sysClient));
	}

	/**
	 * 客户端信息
	 *
	 * @param id id
	 * @return Result
	 */
	@PreAuth
	@Log(value = "客户端信息", exception = "客户端信息请求异常")
	@GetMapping("/get")
	@Operation(summary = "客户端信息", description = "客户端信息,根据ID查询")
	@Parameters({
			@Parameter(name = "id", required = true,  description = "主键ID", in = ParameterIn.DEFAULT),
	})
	public Result<?> get(@RequestParam String id) {
		return Result.data(sysClientService.getById(id));
	}

	/**
	 * 客户端删除
	 *
	 * @param ids 多个id采用逗号分隔
	 * @return Result
	 */
	@PreAuth
	@Log(value = "客户端删除", exception = "客户端删除请求异常")
	@PostMapping("/del")
	@Operation(summary = "客户端删除", description = "客户端删除")
	@Parameters({
			@Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
	})
	public Result<?> del(@RequestParam String ids) {
		if (sysClientService.removeByIds(CollectionUtil.stringToCollection(ids))) {
			return Result.success("删除成功");
		}
		return Result.fail("删除失败");
	}

	/**
	 * 客户端状态
	 *
	 * @param ids    　多个id采用逗号分隔
	 * @param status 　状态包括启用和禁用
	 * @return Result
	 */
	@PreAuth
	@Log(value = "客户端状态", exception = "客户端状态请求异常")
	@PostMapping("/set-status")
	@Operation(summary = "客户端状态", description = "客户端状态：启用、禁用")
	@Parameters({
			@Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT),
			@Parameter(name = "status", required = true,  description = "状态", in = ParameterIn.DEFAULT)
	})
	public Result<?> setStatus(@RequestParam String ids, @RequestParam String status) {
		return Result.condition(sysClientService.status(ids, status));
	}

	/**
	 * 客户端导出
	 */
	@PreAuth
	@Log(value = "客户端导出", exception = "客户端导出请求异常")
	@PostMapping("/export")
	@Operation(summary = "客户端导出", description = "客户端导出")
	public void export(HttpServletResponse response) {
		List<SysClientPOI> sysClientPOIS = sysClientService.export();
		//使用工具类导出excel
		ExcelUtil.exportExcel(sysClientPOIS, null, "客户端列表", SysClientPOI.class, "client", response);
	}
}

