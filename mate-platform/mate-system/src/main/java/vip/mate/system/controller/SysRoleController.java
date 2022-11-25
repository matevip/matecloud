package vip.mate.system.controller;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import vip.mate.core.common.constant.SystemConstant;
import vip.mate.core.database.entity.Search;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.redis.core.RedisService;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
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
@Tag(name = "角色管理")
public class SysRoleController extends BaseController {

    private final ISysRoleService sysRoleService;
    private final ISysRolePermissionService sysRolePermissionService;
    private final RedisService redisService;

    @PreAuth
    @Log(value = "角色分页")
    @GetMapping("/page")
    @Operation(summary = "角色分页")
    @Parameters({
            @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
            @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> page(Search search) {
        return Result.data(sysRoleService.listPage(search));
    }

    /**
     * 角色列表
     *
     * @param query 　关键词查询
     * @return Result
     */
    @PreAuth
    @Log(value = "角色列表", exception = "角色列表请求异常")
    @GetMapping("/list")
    @Operation(summary = "角色列表", description = "角色列表，根据query查询")
    @Parameters({
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysRoleService.listSearch(query));
    }

    /**
     * 所有角色列表
     *
     * @return List
     */
    @PreAuth
    @Log(value = "所有角色列表")
    @GetMapping("/all-list")
    @Operation(summary = "所有角色列表")
    public Result<?> allList() {
        // 设置一个未授权的角色信息，提供给前端使用
        SysRole sysRole = new SysRole();
        sysRole.setId(NumberUtil.parseLong(SystemConstant.ROLE_DEFAULT_ID));
        sysRole.setRoleName(SystemConstant.ROLE_DEFAULT_VALUE);
        // 查询所有角色列表
        List<SysRole> list = sysRoleService.list();
        // 增加未授权角色信息
        list.add(sysRole);
        // 业务返回
        return Result.data(list);
    }

    /**
     * 角色设置
     *
     * @param sysRole SysRole对象
     * @return Result
     */
    @PreAuth
    @Log(value = "角色设置", exception = "角色设置请求异常")
    @PostMapping("/set")
    @Operation(summary = "角色设置", description = "角色设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysRole sysRole) {
        return Result.condition(sysRoleService.set(sysRole));
    }

    /**
     * 角色状态变更
     *
     * @param sysRole
     * @return
     */
    @PreAuth
    @Log(value = "角色状态变更")
    @PostMapping("/set-status")
    @Operation(summary = "角色状态变更")
    public Result<?> setStatus(@Valid @RequestBody SysRole sysRole) {
        return Result.condition(sysRoleService.update(Wrappers.<SysRole>lambdaUpdate().
                set(SysRole::getStatus, sysRole.getStatus())
                .eq(SysRole::getId, sysRole.getId())));
    }

    /**
     * 角色信息
     *
     * @param id id
     * @return Result
     */
    @PreAuth
    @Log(value = "角色信息", exception = "角色信息请求异常")
    @GetMapping("/get")
    @Operation(summary = "角色信息", description = "根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "用户ID", in = ParameterIn.DEFAULT),
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
    @PreAuth
    @Log(value = "角色删除", exception = "角色删除请求异常")
    @PostMapping("/delete")
    @Operation(summary = "角色删除", description = "角色删除，支持批量操作")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> delete(@RequestParam String ids) {
        return Result.condition(sysRoleService.batchDeleteByIds(ids));
    }

    /**
     * 角色权限列表
     *
     * @param id id
     * @return Result
     */
    @PreAuth
    @Log(value = "角色权限列表", exception = "角色权限列表请求异常")
    @GetMapping("/get-permission")
    @Operation(summary = "角色权限列表", description = "角色权限列表")
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
    @PreAuth
    @Log(value = "角色权限设置", exception = "角色权限设置")
    @PostMapping("/set-permission")
    @Operation(summary = "角色权限设置", description = "角色权限设置")
    @Parameters({
            @Parameter(name = "roleId", required = true,  description = "角色ID", in = ParameterIn.DEFAULT),
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
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
        redisService.del("getPermission-" + roleId);
        return Result.success("操作成功");
    }

    /**
     * 角色树
     *
     * @return Result
     */
    @PreAuth
    @Log(value = "角色树", exception = "角色树请求异常")
    @GetMapping("/tree")
    @Operation(summary = "角色树", description = "角色树")
    public Result<?> tree() {
        return Result.data(sysRoleService.tree());
    }

    /**
     * 角色导出
     */
    @PreAuth
    @Log(value = "导出角色", exception = "导出角色")
    @PostMapping("/export")
    @Operation(summary = "导出角色", description = "导出角色")
    public void export(HttpServletResponse response) {
        List<SysRolePOI> sysRolePOIS = sysRoleService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysRolePOIS, null, "角色", SysRolePOI.class, "role", response);
    }
}

