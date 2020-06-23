package vip.mate.system.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.util.TreeUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-menu")
public class SysMenuController extends BaseController {

    private final ISysMenuService sysMenuService;

    @GetMapping("/routes")
    public Result<?> routes() {
        return Result.data(sysMenuService.routes());
    }

    @GetMapping("/list")
    public Result<?> list() {
        return Result.data(TreeUtil.list2Tree(sysMenuService.list(), MateConstant.TREE_ROOT));
    }

    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysMenu sysMenu) {
        if (sysMenuService.saveAll(sysMenu)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

}

