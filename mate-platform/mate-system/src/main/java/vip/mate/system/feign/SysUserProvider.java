package vip.mate.system.feign;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.SystemConstant;
import vip.mate.system.dto.UserInfo;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysRolePermissionService;
import vip.mate.system.service.ISysRoleService;
import vip.mate.system.service.ISysUserService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Feign或者Dubbo调用用户操作")
public class SysUserProvider implements ISysUserProvider {

    private final ISysUserService sysUserService;
    private final ISysRolePermissionService sysRolePermissionService;

    @Override
    @GetMapping("/provider/user-info-by-id")
    @ApiOperation(value = "根据用户ID获取用户信息", notes = "根据用户ID获取用户信息")
    public Result<SysUser> userInfoById(Long userId) {
        SysUser sysUser = sysUserService.getById(userId);
        return Result.data(sysUser);
    }

    @Override
    @GetMapping("/provider/user-info")
    //@Cached(name= SystemConstant.SYS_USER_CACHE, key="#userName", expire = 3600)
    @ApiOperation(value = "根据用户名获取用户信息", notes = "根据用户名获取用户信息")
    public UserInfo loadUserByUserName(String userName) {
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().lambda().in(SysUser::getAccount, userName));
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        userInfo.setPermissions(sysRolePermissionService.getMenuIdByRoleId(sysUser.getRoleId().toString()));
        List<Long> longs = new ArrayList<>();
        longs.add(sysUser.getRoleId());
        userInfo.setRoleIds(longs);
        log.info("feign调用：userInfo:{}", userInfo);
        return userInfo;
    }

}
