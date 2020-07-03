package vip.mate.system.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysRolePermission;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.service.ISysRolePermissionService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
public class SysRolePermissionProvider implements ISysRolePermissionProvider {

    private final ISysRolePermissionService sysRolePermissionService;
    private final ISysMenuService sysMenuService;

    @Override
    @GetMapping("/provider/sys-role-permission/get-permission")
    public List<String> getMenuIdByRoleId(String roleId) {
        //1.根据角色ID，查询菜单列表
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.getMenuIdByRoleId(roleId);
        //2.根据menuId从mate_sys_menu表中查询按钮，也就是type＝2，并返回List
        List<String> strings = sysRolePermissions.stream().map(sysRolePermission -> {
            Long menuId = sysRolePermission.getMenuId();
            LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SysMenu::getId, menuId);
            lambdaQueryWrapper.eq(SysMenu::getType, 2);
            SysMenu sysMenu = sysMenuService.getOne(lambdaQueryWrapper);
            if (ObjectUtils.isEmpty(sysMenu)) {
                return null;
            }
            return sysMenu.getPermission();
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return strings;
    }
}
