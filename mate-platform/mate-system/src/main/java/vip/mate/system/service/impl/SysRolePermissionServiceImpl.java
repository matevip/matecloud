package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysRolePermission;
import vip.mate.system.mapper.SysRolePermissionMapper;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.service.ISysRolePermissionService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-07-02
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements ISysRolePermissionService {

    @Autowired
    private ISysMenuService sysMenuService;

    @Override
    public List<String> getMenuIdByRoleId(String roleId) {
        LambdaQueryWrapper<SysRolePermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRolePermission::getRoleId, roleId);

        //1.根据角色ID，查询菜单列表
        List<SysRolePermission> sysRolePermissions = this.baseMapper.selectList(lambdaQueryWrapper);
        //2.根据menuId从mate_sys_menu表中查询按钮，也就是type＝2，并返回List
        List<String> strings = sysRolePermissions.stream().map(sysRolePermission -> {
            Long menuId = sysRolePermission.getMenuId();
            LambdaQueryWrapper<SysMenu> sysMenuLambdaQueryWrapper = new LambdaQueryWrapper<>();
            sysMenuLambdaQueryWrapper.eq(SysMenu::getId, menuId);
            sysMenuLambdaQueryWrapper.eq(SysMenu::getType, 2);
            SysMenu sysMenu = sysMenuService.getOne(sysMenuLambdaQueryWrapper);
            if (ObjectUtils.isEmpty(sysMenu)) {
                return null;
            }
            return sysMenu.getPermission();
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return strings;
    }
}
