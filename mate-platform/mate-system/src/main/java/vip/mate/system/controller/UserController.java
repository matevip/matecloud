package vip.mate.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.entity.User;
import vip.mate.system.service.IUserService;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-04-21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController extends BaseController {

    private final IUserService userService;

    @GetMapping("/test")
    public Result<List<User>> test(){
        return Result.data(userService.list());
    }

}

