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
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.service.ISysLogService;

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
@RequestMapping("/log")
@Api(tags = "日志管理")
public class SysLogController extends BaseController {

    private final ISysLogService sysLogService;

    /**
     * 日志分页列表
     * @param search　搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "日志列表", exception = "日志列表请求异常")
    @GetMapping("/page")
    @ApiOperation(value = "日志列表", notes = "日志列表")
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
        return Result.data(sysLogService.listPage(search));
    }

    /**
     * 日志删除
     * @param ids　多个id采用逗号分隔
     * @return Result
     */
    @PreAuth(hasPerm = "sys:log:delete")
    @Log(value = "日志删除", exception = "日志删除")
    @PostMapping("/del")
    @ApiOperation(value = "日志删除", notes = "日志删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysLogService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

}

