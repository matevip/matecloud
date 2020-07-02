package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysRole;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysRoleService;

import javax.validation.Valid;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-role")
public class SysRoleController extends BaseController {

    private final ISysRoleService sysRoleService;

    @GetMapping("/list")
    public Result<?> list(){
        return Result.data(sysRoleService.list());
    }

    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysRole sysRole) {
        if (sysRoleService.saveOrUpdate(sysRole)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @GetMapping("/info")
    public Result<?> info(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRole::getId, sysRole.getId());
        return Result.data(sysRoleService.getOne(lambdaQueryWrapper));
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestParam String ids) {
        if (sysRoleService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

