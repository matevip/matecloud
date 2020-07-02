package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.dto.SysMenuDTO;
import vip.mate.system.entity.SysRole;
import vip.mate.system.entity.SysRolePermission;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysRolePermissionService;
import vip.mate.system.service.ISysRoleService;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ISysRolePermissionService sysRolePermissionService;

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

    @GetMapping("/getPermission")
    public Result<?> getPermission(@RequestParam String id) {
        LambdaQueryWrapper<SysRolePermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRolePermission::getRoleId, id);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.list(lambdaQueryWrapper);
        List<Long> list = sysRolePermissions.stream().map(sysRolePermission -> {
            long menuId = sysRolePermission.getMenuId();
            return menuId;
        }).collect(Collectors.toList());
        return Result.data(list);
    }

    @PostMapping("/savePermission")
    public Result<?> savePermission(@RequestParam String roleId, @RequestParam String ids) {
        Collection longs = CollectionUtil.stringToCollection(ids);
        long roleIdc = CollectionUtil.strToLong(roleId, 0L);
        LambdaQueryWrapper<SysRolePermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRolePermission::getRoleId, roleIdc);
        sysRolePermissionService.remove(lambdaQueryWrapper);
        for (Iterator<Long> it = longs.iterator(); it.hasNext();) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            long menuId = it.next();
            sysRolePermission.setMenuId(menuId);
            sysRolePermission.setRoleId(roleIdc);
            sysRolePermissionService.saveOrUpdate(sysRolePermission);
        }
        return Result.data("操作成功");
    }
}

