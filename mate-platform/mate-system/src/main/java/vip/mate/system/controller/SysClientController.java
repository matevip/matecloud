package vip.mate.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.EnableToken;
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
@RequestMapping("/sys-client")
@Api(tags = "系统客户端资源管理")
public class SysClientController extends BaseController {

    private final ISysClientService sysClientService;

    @EnableToken
    @Log(value = "客户端分页列表", exception = "客户端分页列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "获取分页接口列表", notes = "获取分页接口列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(Page page, Search search) {
        return Result.data(sysClientService.listPage(page, search));
    }


    @EnableToken
    @Log(value = "新增或修改客户端", exception = "新增或修改客户端请求异常")
    @PostMapping("/save-or-update")
    @ApiOperation(value = "添加系统客户端", notes = "添加系统客户端,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysClient sysClient) {
        if (sysClientService.saveOrUpdate(sysClient)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "获取系统客户端信息", exception = "获取系统客户端信息请求异常")
    @GetMapping("/info")
    @ApiOperation(value = "获取系统客户端信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
    })
    public Result<?> getSysUser(SysClient sysClient) {
        return Result.data(sysClientService.getById(sysClient.getId()));
    }

    @EnableToken
    @Log(value = "批量删除系统客户端", exception = "批量删除系统客户端请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除系统客户端", notes = "批量删除系统客户端")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysClientService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @EnableToken
    @Log(value = "批量设置系统客户端状态", exception = "批量设置系统客户端状态请求异常")
    @PostMapping("/status")
    @ApiOperation(value = "批量设置系统客户端状态", notes = "状态包括：启用、禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
            @ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
    })
    public Result<?> status(@RequestParam String ids, @RequestParam String status) {
        if (sysClientService.status(ids, status)) {
            return Result.success("批量修改成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "导出客户端列表", exception = "导出客户端列表请求异常")
    @PostMapping("/export-client")
    @ApiOperation(value = "导出客户端列表", notes = "导出客户端列表")
    public void export(HttpServletResponse response) {
        List<SysClientPOI> sysClientPOIS = sysClientService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysClientPOIS, null, "客户端列表", SysClientPOI.class, "client", response);
    }
}

