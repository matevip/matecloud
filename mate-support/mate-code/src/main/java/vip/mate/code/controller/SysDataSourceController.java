package vip.mate.code.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.code.entity.SysDataSource;
import vip.mate.code.service.ISysDataSourceService;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 数据源表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-07-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-data-source")
@Api(tags = "系统数据源管理")
public class SysDataSourceController extends BaseController {

    private final ISysDataSourceService sysDataSourceService;

    @GetMapping("/list")
    @ApiOperation(value = "获取分页接口列表", notes = "获取分页接口列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysDataSourceService.listPage(query));
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取数据源信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
    })
    public Result<?> getSysUser(SysDataSource sysDataSource) {
        return Result.data(sysDataSourceService.getById(sysDataSource.getId()));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加数据源", notes = "添加数据源,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysDataSource sysDataSource) {
        if (sysDataSourceService.saveOrUpdate(sysDataSource)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除数据源", notes = "批量删除数据源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysDataSourceService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

