package vip.mate.oauth.controller;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.auth.service.TokenService;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.oauth.service.CaptchaService;
import vip.mate.system.entity.SysUser;
import vip.mate.system.feign.ISysRolePermissionProvider;
import vip.mate.system.feign.ISysUserProvider;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    @Qualifier("consumerTokenServices")
    private final ConsumerTokenServices consumerTokenServices;

    private final CaptchaService captchaService;

    private final ISysUserProvider sysUserProvider;

    private final ISysRolePermissionProvider sysRolePermissionProvider;

    private final TokenService tokenService;

    @GetMapping("/auth/userInfo")
    public Result<?> getUserInfo(HttpServletRequest request) {
        Claims claims = tokenService.checkToken(request);
        String userName = (String)claims.get("userName");

        SysUser sysUser = sysUserProvider.loadUserByUserName(userName);
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userName);
        data.put("avatar", sysUser.getAvatar());
        data.put("roleId", sysUser.getRoleId());
        data.put("departId", sysUser.getDepartId());
        List<String> stringList = sysRolePermissionProvider.getMenuIdByRoleId(String.valueOf(sysUser.getRoleId()));
        data.put("permissions", stringList);
        return Result.data(data);
    }

    @GetMapping("/auth/code")
    public Result<?> authCode() {
        return captchaService.getCode();
    }

    @PostMapping("/auth/logout")
    public Result<?> logout(HttpServletRequest request) {
        consumerTokenServices.revokeToken(SecurityUtil.getToken(request));
        return Result.success("操作成功");
    }

}
