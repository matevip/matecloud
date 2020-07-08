package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import java.util.Map;
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
@Api(tags = "系统角色资源管理")
public class SysRoleController extends BaseController {

    private final ISysRoleService sysRoleService;
    private final ISysRolePermissionService sysRolePermissionService;

    @GetMapping("/list")
    @ApiOperation(value = "获取角色接口列表", notes = "获取角色接口列表，根据query查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@RequestParam Map<String, String> query){
        return Result.data(sysRoleService.listSearch(query));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加系统角色", notes = "添加系统角色,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysRole sysRole) {
        if (sysRoleService.saveOrUpdate(sysRole)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @GetMapping("/info")
    @ApiOperation(value = "获取用户信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
    })
    public Result<?> info(SysRole sysRole) {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRole::getId, sysRole.getId());
        return Result.data(sysRoleService.getOne(lambdaQueryWrapper));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "批量删除角色数据", notes = "批量删除角色数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysRoleService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @GetMapping("/getPermission")
    @ApiOperation(value = "根据角色ID查询菜单列表", notes = "根据角色ID查询菜单列表")
    public Result<?> getPermission(@RequestParam String id) {
        return Result.data(sysRoleService.getPermission(id));
    }

    @PostMapping("/savePermission")
    @ApiOperation(value = "根据角色ID和菜单ids保存菜单权限", notes = "根据角色ID和菜单ids保存菜单权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", required = true, value = "角色ID", paramType = "form"),
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
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

    @GetMapping("/tree")
    @ApiOperation(value = "获取角色树列表", notes = "获取角色树列表")
    public Result<?> tree() {
        return Result.data(sysRoleService.tree());
    }
}

