package vip.mate.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
@Tag(name = "日志管理")
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
    @Operation(summary = "日志列表", description = "日志列表")
    @Parameters({
            @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
            @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "prop", required = true,  description = "排序属性", in = ParameterIn.DEFAULT),
            @Parameter(name = "order", required = true,  description = "排序方式", in = ParameterIn.DEFAULT),
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
    @Operation(summary = "日志删除", description = "日志删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysLogService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

    @PreAuth()
    @Log(value = "日志清空")
    @PostMapping("/empty")
    @Operation(summary = "日志清空")
    public Result<?> empty() {
        return Result.condition(sysLogService.remove(Wrappers.emptyWrapper()));
    }

}

