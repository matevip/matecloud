package vip.mate.code.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.code.entity.SysDataSource;
import vip.mate.code.service.ISysDataSourceService;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;
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
@RequestMapping("/data-source")
@Tag(name = "数据源管理")
public class SysDataSourceController extends BaseController {

    private final ISysDataSourceService sysDataSourceService;

    /**
     * 数据源分页
     * @param query　关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "数据源分页", exception = "数据源分页请求异常")
    @GetMapping("/page")
    @Operation(summary = "数据源分页", description = "数据源分页")
    @Parameters({
            @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
            @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> page(@RequestParam Map<String, String> query) {
        return Result.data(sysDataSourceService.listPage(query));
    }

    /**
     * 获取数据源信息
     * @param id id
     * @return Result
     */
    @PreAuth
    @Log(value = "数据源信息", exception = "数据源信息请求异常")
    @GetMapping("/get")
    @Operation(summary = "数据源信息", description = "数据源信息,根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "用户ID", in = ParameterIn.DEFAULT),
    })
    public Result<?> get(@RequestParam String id) {
        return Result.data(sysDataSourceService.getById(id));
    }

    /**
     * 数据源设置
     * @param sysDataSource　SysDataSource对象
     * @return Result
     */
    @PreAuth
    @Log(value = "数据源设置", exception = "数据源设置请求异常")
    @PostMapping("/set")
    @Operation(summary = "数据源设置", description = "数据源设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysDataSource sysDataSource) {
        return Result.condition(sysDataSourceService.saveOrUpdate(sysDataSource));
    }

    /**
     * 数据源删除
     * @param ids　多个id采用逗号分隔
     * @return Result
     */
    @PreAuth
    @Log(value = "数据源删除", exception = "数据源删除请求异常")
    @PostMapping("/del")
    @Operation(summary = "数据源删除", description = "数据源删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysDataSourceService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

    /**
     * 数据源项列表
     * @return Result
     */
    @PreAuth
    @Log(value = "数据源项列表", exception = "数据源项列表请求异常")
    @GetMapping("/option-list")
    @Operation(summary = "数据源项列表", description = "数据源项列表")
    public Result<?> optionList() {
        return Result.data(sysDataSourceService.optionList());
    }
}

