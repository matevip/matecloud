package vip.mate.system.feign;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.ProviderConstant;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.util.TrackUtil;
import vip.mate.system.dto.UserInfo;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysRolePermissionService;
import vip.mate.system.service.ISysUserService;

/**
 * 远程调用获取用户信息
 *
 * @author xuzhanfu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "用户远程调用")
public class SysUserProvider implements ISysUserProvider {

    private final ISysUserService sysUserService;
    private final ISysRolePermissionService sysRolePermissionService;

    @Override
    @GetMapping(ProviderConstant.PROVIDER_USER_ID)
    @Log(value = "用户ID查询", exception = "用户ID查询请求失败")
    @ApiOperation(value = "用户ID查询", notes = "用户ID查询")
    public Result<SysUser> getUserById(Long id) {
        SysUser sysUser = sysUserService.getById(id);
        // 测试日志埋点
        TrackUtil.info(SysUserProvider.class.getName(), "userInfoById", JSON.toJSONString(sysUser));
        return Result.data(sysUser);
    }


    @Override
    @GetMapping(ProviderConstant.PROVIDER_USER_USERNAME)
    @Log(value = "用户名查询用户", exception = "用户名查询用户请求失败")
    @ApiOperation(value = "用户用户名查询", notes = "用户用户名查询")
    public Result<UserInfo> getUserByUserName(String userName) {
        SysUser sysUser = sysUserService.getOneIgnoreTenant(new SysUser().setAccount(userName));
        return Result.data(getUserInfo(sysUser));
    }

    @Override
    @GetMapping(ProviderConstant.PROVIDER_USER_MOBILE)
    @Log(value = "用户手机号查询", exception = "用户手机号查询请求失败")
    @ApiOperation(value = "用户手机号查询", notes = "用户手机号查询")
    public Result<UserInfo> getUserByMobile(String mobile) {

        SysUser sysUser = sysUserService.getOneIgnoreTenant(new SysUser().setTelephone(mobile));
        return Result.data(getUserInfo(sysUser));
    }

    public UserInfo getUserInfo(SysUser sysUser) {
        if (sysUser == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        userInfo.setPermissions(sysRolePermissionService.getMenuIdByRoleId(sysUser.getRoleId()));
        userInfo.setRoleIds(ListUtil.toList(sysUser.getRoleId()));
        userInfo.setTenantId(sysUser.getTenantId());
        log.debug("feign调用：userInfo:{}", userInfo);
        return userInfo;
    }

}
