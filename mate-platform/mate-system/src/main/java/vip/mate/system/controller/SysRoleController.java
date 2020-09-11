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
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.system.entity.SysRole;
import vip.mate.system.entity.SysRolePermission;
import vip.mate.system.poi.SysRolePOI;
import vip.mate.system.service.ISysRolePermissionService;
import vip.mate.system.service.ISysRoleService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/role")
@Api(tags = "角色管理")
public class SysRoleController extends BaseController {

    private final ISysRoleService sysRoleService;
    private final ISysRolePermissionService sysRolePermissionService;

    /**
     * 角色列表
     *
     * @param query 　关键词查询
     * @return Result
     */
    @EnableToken
    @Log(value = "角色列表", exception = "角色列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "角色列表", notes = "角色列表，根据query查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysRoleService.listSearch(query));
    }

    /**
     * 角色设置
     *
     * @param sysRole SysRole对象
     * @return Result
     */
    @EnableToken
    @Log(value = "角色设置", exception = "角色设置请求异常")
    @PostMapping("/set")
    @ApiOperation(value = "角色设置", notes = "角色设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysRole sysRole) {
        return Result.condition(sysRoleService.saveOrUpdate(sysRole));
    }

    /**
     * 角色信息
     *
     * @param id id
     * @return Result
     */
    @EnableToken
    @Log(value = "角色信息", exception = "角色信息请求异常")
    @GetMapping("/get")
    @ApiOperation(value = "角色信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
    })
    public Result<?> get(@RequestParam String id) {
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysRole::getId, id);
        return Result.data(sysRoleService.getOne(lambdaQueryWrapper));
    }

    /**
     * 角色删除
     *
     * @param ids 多个id使用逗号分隔
     * @return Result
     */
    @EnableToken
    @Log(value = "角色删除", exception = "角色删除请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "角色删除", notes = "角色删除，支持批量操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        return Result.condition(sysRoleService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

    /**
     * 角色权限列表
     *
     * @param id id
     * @return Result
     */
    @EnableToken
    @Log(value = "角色权限列表", exception = "角色权限列表请求异常")
    @GetMapping("/get-permission")
    @ApiOperation(value = "角色权限列表", notes = "角色权限列表")
    public Result<?> getPermission(@RequestParam String id) {
        return Result.data(sysRoleService.getPermission(id));
    }

    /**
     * 角色权限设置
     *
     * @param roleId 　角色Id
     * @param ids    多个id使用逗号分隔
     * @return Result
     */
    @EnableToken
    @Log(value = "角色权限设置", exception = "角色权限设置")
    @PostMapping("/set-permission")
    @ApiOperation(value = "角色权限设置", notes = "角色权限设置")
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
        for (Iterator<Long> it = longs.iterator(); it.hasNext(); ) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            long menuId = it.next();
            sysRolePermission.setMenuId(menuId);
            sysRolePermission.setRoleId(roleIdc);
            sysRolePermissionService.saveOrUpdate(sysRolePermission);
        }
        return Result.success("操作成功");
    }

    /**
     * 角色树
     *
     * @return Result
     */
    @EnableToken
    @Log(value = "角色树", exception = "角色树请求异常")
    @GetMapping("/tree")
    @ApiOperation(value = "角色树", notes = "角色树")
    public Result<?> tree() {
        return Result.data(sysRoleService.tree());
    }

    /**
     * 角色导出
     */
    @EnableToken
    @Log(value = "导出角色", exception = "导出角色")
    @PostMapping("/export")
    @ApiOperation(value = "导出角色", notes = "导出角色")
    public void export(@ApiIgnore HttpServletResponse response) {
        List<SysRolePOI> sysRolePOIS = sysRoleService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysRolePOIS, null, "角色", SysRolePOI.class, "role", response);
    }
}

