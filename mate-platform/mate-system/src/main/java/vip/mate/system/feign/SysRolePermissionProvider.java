package vip.mate.system.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.system.service.ISysRolePermissionService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Feign或者Dubbo调用角色菜单操作")
public class SysRolePermissionProvider implements ISysRolePermissionProvider {

    private final ISysRolePermissionService sysRolePermissionService;

    @Override
    @GetMapping("/provider/sys-role-permission/get-permission")
    @ApiOperation(value = "根据角色ID获取菜单列表", notes = "根据角色ID获取菜单列表")
    public List<String> getMenuIdByRoleId(String roleId) {
       return sysRolePermissionService.getMenuIdByRoleId(roleId);
    }
}
