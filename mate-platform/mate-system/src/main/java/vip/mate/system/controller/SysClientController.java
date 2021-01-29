package vip.mate.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "客户端管理")
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
	@ApiOperation(value = "客户端分页", notes = "客户端分页")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
			@ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
			@ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
			@ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
			@ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
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
	@ApiOperation(value = "客户端设置", notes = "客户端设置,支持新增或修改")
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
	@ApiOperation(value = "客户端信息", notes = "客户端信息,根据ID查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
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
	@ApiOperation(value = "客户端删除", notes = "客户端删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
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
	@ApiOperation(value = "客户端状态", notes = "客户端状态：启用、禁用")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
			@ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
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
	@ApiOperation(value = "客户端导出", notes = "客户端导出")
	public void export(HttpServletResponse response) {
		List<SysClientPOI> sysClientPOIS = sysClientService.export();
		//使用工具类导出excel
		ExcelUtil.exportExcel(sysClientPOIS, null, "客户端列表", SysClientPOI.class, "client", response);
	}
}

