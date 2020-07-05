package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
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

    @EnableToken
    @GetMapping("/list")
    public Result<?> list(@ApiIgnore @RequestParam Map<String, Object> search) {
       return Result.data(sysDepartService.searchList(search));
    }

    @EnableToken
    @GetMapping("/tree")
    public Result<?> tree() {
        return Result.data(ForestNodeMerger.merge(sysDepartService.tree()));
    }

    @EnableToken
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysDepart sysDepart) {
        if (sysDepartService.saveOrUpdate(sysDepart)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @GetMapping("/info")
    public Result<?> info(SysDepart sysDepart) {
        LambdaQueryWrapper<SysDepart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysDepart::getId, sysDepart.getId());
        return Result.data(sysDepartService.getOne(queryWrapper));
    }

    @EnableToken
    @PostMapping("/delete")
    public Result<?> delete(@RequestParam String ids) {
        if(sysDepartService.removeByIds(CollectionUtil.stringToCollection(ids))) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

