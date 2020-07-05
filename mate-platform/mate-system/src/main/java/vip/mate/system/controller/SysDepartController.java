package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysDepart;
import vip.mate.system.service.ISysDepartService;

import javax.validation.Valid;
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
@RequestMapping("/sys-depart")
@Api(tags = "系统部门资源管理")
public class SysDepartController extends BaseController {

    private final ISysDepartService sysDepartService;

    @GetMapping("/list")
    @ApiOperation(value = "获取系统部门资源列表", notes = "获取系统部门资源列表，根据query查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@ApiIgnore @RequestParam Map<String, Object> search) {
       return Result.data(sysDepartService.searchList(search));
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取部门树列表", notes = "获取部门树列表")
    public Result<?> tree() {
        return Result.data(ForestNodeMerger.merge(sysDepartService.tree()));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加系统部门资源", notes = "添加系统部门资源,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysDepart sysDepart) {
        if (sysDepartService.saveOrUpdate(sysDepart)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取系统部门信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "菜单ID", paramType = "form"),
    })
    public Result<?> info(SysDepart sysDepart) {
        LambdaQueryWrapper<SysDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDepart::getId, sysDepart.getId());
        return Result.data(sysDepartService.getOne(queryWrapper));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除系统部门资源数据", notes = "批量删除系统部门资源数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if(sysDepartService.removeByIds(CollectionUtil.stringToCollection(ids))) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

