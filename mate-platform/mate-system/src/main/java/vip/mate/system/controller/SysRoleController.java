package vip.mate.system.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.service.ISysRoleService;

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

}

