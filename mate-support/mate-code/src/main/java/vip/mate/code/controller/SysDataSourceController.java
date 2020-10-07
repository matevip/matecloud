package vip.mate.code.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "数据源管理")
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
    @ApiOperation(value = "数据源分页", notes = "数据源分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
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
    @ApiOperation(value = "数据源信息", notes = "数据源信息,根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
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
    @ApiOperation(value = "数据源设置", notes = "数据源设置,支持新增或修改")
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
    @ApiOperation(value = "数据源删除", notes = "数据源删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
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
    @ApiOperation(value = "数据源项列表", notes = "数据源项列表")
    public Result<?> optionList() {
        return Result.data(sysDataSourceService.optionList());
    }
}

