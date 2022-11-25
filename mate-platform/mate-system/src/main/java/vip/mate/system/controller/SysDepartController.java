package vip.mate.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysDepart;
import vip.mate.system.poi.SysDepartPOI;
import vip.mate.system.service.ISysDepartService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */

@RestController
@AllArgsConstructor
@RequestMapping("/depart")
@Tag(name = "部门管理")
public class SysDepartController extends BaseController {

    private final ISysDepartService sysDepartService;

    /**
     * 部门列表
     *
     * @param search 　根据关键字搜索
     * @return Result
     */
    @PreAuth
    @Log(value = "部门列表", exception = "部门列表请求异常")
    @GetMapping("/list")
    @Operation(summary = "部门列表", description = "部门列表，根据search查询")
    @Parameters({
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> list(@RequestParam Map<String, Object> search) {
        return Result.data(sysDepartService.searchList(search));
    }

    /**
     * 部门树
     *
     * @return Result
     */
    @PreAuth
    @Log(value = "部门树", exception = "部门树请求异常")
    @GetMapping("/tree")
    @Operation(summary = "部门树", description = "部门树")
    public Result<?> tree() {
        return Result.data(ForestNodeMerger.merge(sysDepartService.tree()));
    }

    /**
     * 部门设置
     *
     * @param sysDepart 　SysDepart对象
     * @return Result
     */
    @PreAuth
    @PostMapping("/set")
    @Log(value = "部门设置", exception = "部门设置异常")
    @Operation(summary = "部门设置", description = "部门设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysDepart sysDepart) {
        return Result.condition(sysDepartService.saveOrUpdate(sysDepart));
    }

    /**
     * 部门信息
     *
     * @param id id
     * @return Result
     */
    @PreAuth
    @GetMapping("/get")
    @Log(value = "部门信息", exception = "部门信息息请求异常")
    @Operation(summary = "部门信息", description = "部门信息,根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "菜单ID", in = ParameterIn.DEFAULT),
    })
    public Result<?> get(@RequestParam String id) {
        LambdaQueryWrapper<SysDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDepart::getId, id);
        return Result.data(sysDepartService.getOne(queryWrapper));
    }

    /**
     * 部门删除
     *
     * @param ids 　多个id采用逗号分隔
     * @return Result
     */
    @PreAuth
    @PostMapping("/del")
    @Log(value = "部门删除", exception = "部门删除请求异常")
    @Operation(summary = "部门删除", description = "部门删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysDepartService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

    /**
     * 部门导出
     */
    @PreAuth
    @PostMapping("/export")
    @Log(value = "部门导出", exception = "部门导出请求异常")
    @Operation(summary = "部门导出", description = "部门导出")
    public void export(HttpServletResponse response) {
        List<SysDepartPOI> sysDepartPOIS = sysDepartService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysDepartPOIS, null, "部门", SysDepartPOI.class, "depart", response);
    }
}

