package vip.mate.system.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.constant.ProviderConstant;
import vip.mate.core.log.annotation.Log;
import vip.mate.system.service.ISysRolePermissionService;

import java.util.List;

/**
 * 远程调用获取角色权限信息
 * @author pangu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "角色权限远程调用")
public class SysRolePermissionProvider implements ISysRolePermissionProvider {

    private final ISysRolePermissionService sysRolePermissionService;

    @Override
    @GetMapping(ProviderConstant.PROVIDER_ROLE_PERMISSION)
    @Log(value = "获取菜单列表", exception = "获取菜单列表请求失败")
    @ApiOperation(value = "获取菜单列表", notes = "根据角色ID获取菜单列表")
    public List<String> getMenuIdByRoleId(String roleId) {
       return sysRolePermissionService.getMenuIdByRoleId(roleId);
    }
}
