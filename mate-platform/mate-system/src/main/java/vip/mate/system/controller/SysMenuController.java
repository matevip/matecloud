package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.annotation.EnableUser;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.core.web.util.ExcelUtil;
import vip.mate.system.dto.SysMenuDTO;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.poi.SysMenuPOI;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.util.TreeUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/sys-menu")
@Api(tags = "系统菜单资源管理")
public class SysMenuController extends BaseController {

    private final ISysMenuService sysMenuService;

    @EnableToken
    @Log(value = "根据RoleId查询routes列表", exception = "根据RoleId查询routes列表请求异常")
    @GetMapping("/routes")
    @ApiOperation(value = "根据RoleId查询routes列表", notes = "根据RoleId查询routes列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", required = false, value = "可不填，根据token获取", paramType = "form"),
    })
    public Result<?> routes(@ApiIgnore @EnableUser LoginUser user) {
        return Result.data(sysMenuService.routes(user.getRoleId()));
    }

    @EnableToken
    @Log(value = "获取菜单接口列表", exception = "获取菜单接口列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "获取菜单接口列表", notes = "获取菜单接口列表，根据query查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@ApiIgnore @RequestParam Map<String, Object> search) {
        return Result.data(TreeUtil.list2Tree(sysMenuService.searchList(search), MateConstant.TREE_ROOT));
    }

    @EnableToken
    @Log(value = "查询所有系统菜单资源列表", exception = "查询所有系统菜单资源列表请求异常")
    @GetMapping("/asyncList")
    @ApiOperation(value = "查询所有系统菜单资源列表", notes = "查询所有菜单列表")
    public Result<?> asyncList(){
        List<SysMenu> sysMenus = sysMenuService.list();
        List<SysMenuDTO> sysMenuDTOS = sysMenus.stream().map(sysMenu -> {
            SysMenuDTO sysMenuDTO = new SysMenuDTO();
            sysMenuDTO.setId(sysMenu.getId());
            sysMenuDTO.setLabel(sysMenu.getName());
            sysMenuDTO.setParentId(sysMenu.getParentId());
            return sysMenuDTO;
        }).collect(Collectors.toList());
        return Result.data(ForestNodeMerger.merge(sysMenuDTOS));
    }

    @EnableToken
    @Log(value = "新增或修改菜单", exception = "新增或修改菜单请求异常")
    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加系统菜单", notes = "添加系统菜单,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysMenu sysMenu) {
        if (sysMenuService.saveAll(sysMenu)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "获取系统菜单信息", exception = "获取系统菜单信息请求异常")
    @GetMapping("/info")
    @ApiOperation(value = "获取系统菜单信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "菜单ID", paramType = "form"),
    })
    public Result<?> getSysMenu(SysMenu sysMenu){
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysMenu::getId, sysMenu.getId());
        return Result.data(sysMenuService.getOne(queryWrapper));
    }

    @EnableToken
    @Log(value = "批量删除系统菜单数据", exception = "批量删除系统菜单数据请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除系统菜单数据", notes = "批量删除系统菜单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
//    @PreAuthorize("@mp.hasPerm('sys:menu:delete')")
    public Result<?> delete(@RequestParam String ids) {
        if(sysMenuService.removeByIds(CollectionUtil.stringToCollection(ids))) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @Log(value = "批量设置菜单状态", exception = "批量设置菜单状态请求异常")
    @PostMapping("/status")
    @ApiOperation(value = "批量设置菜单状态", notes = "状态包括：启用、禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
            @ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
    })
    public Result<?> status(@RequestParam String ids, @RequestParam String status) {
        if (sysMenuService.status(ids, status)){
            return Result.success("批量修改成功");
        }
        return Result.fail("操作失败");
    }

    @Log(value = "导出菜单列表", exception = "导出菜单列表请求异常")
    @PostMapping("/export-menu")
    @ApiOperation(value = "导出菜单列表", notes = "导出菜单列表")
    public void export(@ApiIgnore HttpServletResponse response) {
        List<SysMenuPOI> sysMenuPOIS = sysMenuService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysMenuPOIS, null, "菜单", SysMenuPOI.class, "menu", response);
    }
}

