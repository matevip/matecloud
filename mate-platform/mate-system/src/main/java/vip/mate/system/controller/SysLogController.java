package vip.mate.system.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.service.ISysLogService;

import java.util.Map;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-07-15
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-log")
public class SysLogController extends BaseController {

    private final ISysLogService sysLogService;

    @EnableToken
    @Log(value = "日志分页列表", exception = "日志分页列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "获取日志分页列表", notes = "获取日志分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysLogService.listPage(query));
    }

    @EnableToken
    @Log(value = "批量删除日志", exception = "批量删除日志请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除日志", notes = "批量删除日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysLogService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

}

