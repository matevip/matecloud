package vip.mate.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.database.entity.Search;
import vip.mate.core.file.util.ExcelUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.annotation.EnableUser;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.dto.SysMenuDTO;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.poi.SysMenuPOI;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.util.TreeUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
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
@RequestMapping("/menu")
@Tag(name = "菜单管理")
public class SysMenuController extends BaseController {

    private final ISysMenuService sysMenuService;

    /**
     * 菜单树
     *
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单树", exception = "菜单树请求异常")
    @GetMapping("/tree")
    @Operation(summary = "菜单树", description = "根据roleId查询菜单树")
    public Result<?> tree(@EnableUser LoginUser user) {
        return Result.data(sysMenuService.routes(user.getRoleId()));
    }

    /**
     * 菜单列表
     *
     * @param search 搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单列表", exception = "菜单列表请求异常")
    @GetMapping("/list")
    @Operation(summary = "菜单列表", description = "菜单列表，根据关键词查询")
    @Parameters({
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> list(Search search) {
        return Result.data(ForestNodeMerger.merge(TreeUtil.buildTree(sysMenuService.searchList(search))));
    }

    /**
     * 菜单分级列表，用于前端下拉框使用
     *
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单分级列表", exception = "菜单分级列表请求异常")
    @GetMapping("/grade")
    @Operation(summary = "菜单分级列表", description = "菜单分级列表")
    public Result<?> grade() {
        LambdaQueryWrapper<SysMenu> lsm = Wrappers.<SysMenu>query().lambda().orderByAsc(SysMenu::getSort);
        List<SysMenu> sysMenus = sysMenuService.list(lsm);
        return Result.data(ForestNodeMerger.merge(
                sysMenus.stream().map(sysMenu -> SysMenuDTO.builder()
                        .id(sysMenu.getId())
                        .label(sysMenu.getName())
                        .parentId(sysMenu.getParentId()).build()).collect(Collectors.toList())));
    }

    /**
     * 菜单设置
     *
     * @param sysMenu 菜单
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单设置", exception = "菜单设置请求异常")
    @PostMapping("/set")
    @Operation(summary = "菜单设置", description = "菜单设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysMenu sysMenu) {
        return Result.condition(sysMenuService.saveAll(sysMenu));
    }

    /**
     * 查询菜单信息
     *
     * @param id id
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单信息", exception = "菜单信息请求异常")
    @GetMapping("/get")
    @Operation(summary = "菜单信息", description = "根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "菜单ID", in = ParameterIn.DEFAULT),
    })
    public Result<?> get(@RequestParam String id) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysMenu::getId, id);
        return Result.data(sysMenuService.getOne(queryWrapper));
    }

    /**
     * 批量删除查询
     *
     * @param ids 多个Id，用,号分隔
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单删除", exception = "菜单删除请求异常")
    @PostMapping("/del")
    @Operation(summary = "菜单删除", description = "菜单删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysMenuService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }

    /**
     * 菜单状态
     *
     * @param ids    多个Id，用,号分隔
     * @param status 状态：启用、禁用
     * @return Result
     */
    @PreAuth
    @Log(value = "菜单状态", exception = "菜单状态请求异常")
    @PostMapping("/set-status")
    @Operation(summary = "菜单状态", description = "状态包括：启用、禁用")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个id用,号隔开", in = ParameterIn.DEFAULT),
            @Parameter(name = "status", required = true,  description = "状态", in = ParameterIn.DEFAULT)
    })
    public Result<?> setStatus(@RequestParam String ids, @RequestParam String status) {
        return Result.condition(sysMenuService.status(ids, status));
    }

    /**
     * 菜单是否包含子菜单
     *
     * @param sysMenu 菜单对象
     * @return boolean
     */
    @PreAuth
    @Log(value = "菜单是否包含子菜单")
    @GetMapping("/check-child")
    @Operation(summary = "菜单是否包含子菜单")
    public Result<?> checkChild(SysMenu sysMenu) {
        return Result.data(sysMenuService.checkChild(sysMenu.getId()));
    }

    /**
     * 菜单导出
     */
    @PreAuth
    @Log(value = "菜单导出", exception = "菜单导出请求异常")
    @PostMapping("/export")
    @Operation(summary = "菜单导出", description = "菜单导出")
    public void export(HttpServletResponse response) {
        List<SysMenuPOI> sysMenuPOIS = sysMenuService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysMenuPOIS, null, "菜单", SysMenuPOI.class, "menu", response);
    }
}

