package vip.mate.system.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.service.ISysMenuService;

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
    public Result<Object> routes() {
        return Result.data(sysMenuService.routes());
    }

}

