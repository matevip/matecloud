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
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysBlacklist;
import vip.mate.system.service.ISysBlacklistService;

import javax.validation.Valid;

/**
 * <p>
 * 系统黑名单表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-08-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-blacklist")
@Api(tags = "系统黑名单管理")
public class SysBlacklistController extends BaseController {

    private final ISysBlacklistService sysBlacklistService;

    @EnableToken
    @Log(value = "黑名单分页列表", exception = "黑名单分页列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "获取黑名单列表", notes = "获取黑名单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(Page page, Search search) {
        return Result.data(sysBlacklistService.listPage(page, search));
    }

    @EnableToken
    @Log(value = "新增或修改黑名单", exception = "新增或修改黑名单请求异常")
    @PostMapping("/save-or-update")
    @ApiOperation(value = "添加系统黑名单", notes = "添加系统黑名单,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysBlacklist sysBlacklist) {
        if (sysBlacklistService.saveOrUpdate(sysBlacklist)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "获取黑名单", exception = "获取黑名单请求异常")
    @GetMapping("/info")
    @ApiOperation(value = "获取黑名单", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
    })
    public Result<?> info(SysBlacklist sysBlacklist) {
        return Result.data(sysBlacklistService.getById(sysBlacklist.getId()));
    }

    @EnableToken
    @Log(value = "批量删除黑名单", exception = "批量删除黑名单请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除黑名单", notes = "批量删除黑名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysBlacklistService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }


}

