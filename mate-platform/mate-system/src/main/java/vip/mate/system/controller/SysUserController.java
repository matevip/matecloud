package vip.mate.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysUserService;

import java.util.List;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    @GetMapping("/test")
    public Result<List<SysUser>> test() {
        return Result.data(sysUserService.list());
    }

}

