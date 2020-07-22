package vip.mate.uaa.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.system.dto.UserInfo;
import vip.mate.system.feign.ISysRolePermissionProvider;
import vip.mate.system.feign.ISysUserProvider;
import vip.mate.uaa.service.ValidateService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "认证资源管理")
public class AuthController {

    @Qualifier("consumerTokenServices")
    private final ConsumerTokenServices consumerTokenServices;

    private final ValidateService captchaService;

    private final ISysUserProvider sysUserProvider;

    private final ISysRolePermissionProvider sysRolePermissionProvider;

    @Log(value = "获取用户信息给VUE", exception = "获取用户信息给VUE请求异常")
    @GetMapping("/auth/userInfo")
    @ApiOperation(value = "获取用户信息给VUE", notes = "获取用户信息给VUE")
    public Result<?> getUserInfo(HttpServletRequest request) {

        LoginUser loginUser = SecurityUtil.getUsername(request);
        UserInfo userInfo = null;
        /**
         * 根据type来判断调用哪个接口登录，待扩展社交登录模式
         * type 1:用户名和密码登录　2：手机号码登录
         */
        if (loginUser.getType() == 2) {
            userInfo = sysUserProvider.loadUserByMobile(loginUser.getAccount());
        } else {
            userInfo = sysUserProvider.loadUserByUserName(loginUser.getAccount());
        }

        Map<String, Object> data = new HashMap<>();
        data.put("userName", loginUser.getAccount());
        data.put("avatar", userInfo.getSysUser().getAvatar());
        data.put("roleId", userInfo.getSysUser().getRoleId());
        data.put("departId", userInfo.getSysUser().getDepartId());
        List<String> stringList = sysRolePermissionProvider.getMenuIdByRoleId(String.valueOf(userInfo.getSysUser().getRoleId()));
        data.put("permissions", stringList);
        return Result.data(data);
    }

    @Log(value = "获取验证码", exception = "获取验证码请求异常")
    @GetMapping("/auth/code")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public Result<?> authCode() {
        return captchaService.getCode();
    }

    @Log(value = "退出登录并删除TOKEN", exception = "退出登录并删除TOKEN请求异常")
    @PostMapping("/auth/logout")
    @ApiOperation(value = "退出登录并删除TOKEN", notes = "退出登录并删除TOKEN")
    public Result<?> logout(HttpServletRequest request) {
        consumerTokenServices.revokeToken(SecurityUtil.getToken(request));
        return Result.success("操作成功");
    }

    @Log(value = "获取手机验证码", exception = "获取手机验证码请求异常")
    @ApiOperation(value = "获取手机验证码", notes = "获取手机验证码")
    @GetMapping("/auth/sms-code")
    public Result<?> smsCode(String mobile) {
        return captchaService.getSmsCode(mobile);
    }

}
