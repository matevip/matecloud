package vip.mate.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.cloud.util.CryptoUtil;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysUser;
import vip.mate.system.poi.SysUserPOI;
import vip.mate.system.service.ISysUserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(tags = "用户管理")
public class SysUserController extends BaseController {

	private final ISysUserService sysUserService;

	private final PasswordEncoder passwordEncoder;

	/**
	 * 用户列表
	 *
	 * @param search 　搜索关键词
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户列表", exception = "用户列表请求异常")
	@GetMapping("/page")
	@ApiOperation(value = "用户列表", notes = "分页查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
			@ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
			@ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
			@ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
			@ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
			@ApiImplicitParam(name = "prop", required = true, value = "排序属性", paramType = "form"),
			@ApiImplicitParam(name = "order", required = true, value = "排序方式", paramType = "form"),
	})
	public Result<?> page(Search search) {
		return Result.data(sysUserService.listPage(search));
	}

	/**
	 * 设置用户，支持新增或修改
	 *
	 * @param sysUser 用户信息
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户设置", exception = "设置用户请求异常")
	@PostMapping("/set")
	@ApiOperation(value = "设置用户", notes = "新增或修改用户")
	public Result<?> set(@Valid @RequestBody SysUser sysUser) {
		String password = sysUser.getPassword();
		if (StringUtils.isNotBlank(password) && sysUser.getId() == null) {
			password = passwordEncoder.encode(CryptoUtil.encodeMD5(password));
			sysUser.setPassword(password);
		}
		return Result.condition(sysUserService.saveOrUpdate(sysUser));
	}

	/**
	 * 用户信息
	 *
	 * @param id Id信息
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户信息", exception = "用户信息请求异常")
	@GetMapping("/get")
	@ApiOperation(value = "用户信息", notes = "根据ID查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
	})
	public Result<?> get(@RequestParam String id) {
		return Result.data(sysUserService.getById(id));
	}

	/**
	 * 用户删除
	 *
	 * @param ids id字符串，根据,号分隔
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户删除", exception = "用户删除请求异常")
	@PostMapping("/del")
	@ApiOperation(value = "用户删除", notes = "用户删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
	})
	public Result<?> del(@RequestParam String ids) {
		return Result.condition(sysUserService.removeByIds(CollectionUtil.stringToCollection(ids)));
	}

	/**
	 * 设置用户状态
	 *
	 * @param ids    id字符串，根据,号分隔
	 * @param status 状态标识，启用或禁用
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户状态", exception = "用户状态请求异常")
	@PostMapping("/set-status")
	@ApiOperation(value = "用户状态", notes = "状态包括：启用、禁用")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
			@ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
	})
	public Result<?> setStatus(@RequestParam String ids, @RequestParam String status) {
		return Result.condition(sysUserService.status(ids, status));
	}

	/**
	 * 设置用户密码
	 *
	 * @param id       id
	 * @param password 密码
	 * @return Result
	 */
	@PreAuth
	@Log(value = "用户密码设置", exception = "用户密码设置请求异常")
	@PostMapping("/set-password")
	@ApiOperation(value = "用户密码设置", notes = "用户密码设置")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
			@ApiImplicitParam(name = "password", required = true, value = "密码", paramType = "form")
	})
	public Result<?> setPassword(@RequestParam String id, @RequestParam String password) {
		String pwd = null;
		if (StringUtils.isNotBlank(password)) {
			pwd = passwordEncoder.encode(CryptoUtil.encodeMD5(password));
		}
		SysUser sysUser = new SysUser();
		sysUser.setId(CollectionUtil.strToLong(id, 0L));
		sysUser.setPassword(pwd);
		return Result.condition(sysUserService.updateById(sysUser));
	}

	/**
	 * 用户信息导出
	 */
	@PreAuth
	@Log(value = "用户导出", exception = "导出用户请求异常")
	@PostMapping("/export")
	@ApiOperation(value = "导出用户", notes = "导出用户")
	public void export(@ApiIgnore HttpServletResponse response) {
		List<SysUserPOI> sysUserPOIS = sysUserService.export();
		//使用工具类导出excel
		ExcelUtil.exportExcel(sysUserPOIS, null, "用户", SysUserPOI.class, "user", response);
	}
}

