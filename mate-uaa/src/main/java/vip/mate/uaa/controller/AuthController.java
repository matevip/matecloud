package vip.mate.uaa.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.xkcoding.justauth.AuthRequestFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthDefaultSource;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.system.dto.UserInfo;
import vip.mate.system.feign.ISysRolePermissionProvider;
import vip.mate.system.feign.ISysUserProvider;
import vip.mate.uaa.service.ValidateService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private final AuthRequestFactory factory;

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



    /**
     * 登录类型
     */
    @GetMapping("/auth/list")
    public Map<String, String> loginType() {
        List<String> oauthList = factory.oauthList();
        return oauthList.stream().collect(Collectors.toMap(oauth -> oauth.toLowerCase() + "登录", oauth -> "http://localhost:10001/mate-uaa/auth/login/" + oauth.toLowerCase()));
    }

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     * @param response  response
     * @throws IOException
     */
    @RequestMapping("/auth/login/{oauthType}")
    public void renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(oauthType);
        response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }

//    /**
//     * 登录成功后的回调
//     *
//     * @param oauthType 第三方登录类型
//     * @param callback  携带返回的信息
//     * @return 登录成功后的信息
//     */
//    @RequestMapping("/auth/{oauthType}/callback")
//    public AuthResponse login(@PathVariable String oauthType, AuthCallback callback) {
//        AuthRequest authRequest = factory.get(oauthType);
//        AuthResponse response = authRequest.login(callback);
//        log.info("【response】= {}", JSON.toJSON(response));
//        return response;
//    }

}
