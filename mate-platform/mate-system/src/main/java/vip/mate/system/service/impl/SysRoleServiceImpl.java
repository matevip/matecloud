package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysRole;
import vip.mate.system.entity.SysRolePermission;
import vip.mate.system.mapper.SysRoleMapper;
import vip.mate.system.service.ISysRolePermissionService;
import vip.mate.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.vo.SysRoleVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRolePermissionService sysRolePermissionService;

    @Override
    public List<SysRoleVO> tree() {
        return this.baseMapper.tree();
    }

    @Override
    public List<SysRole> listSearch(Map<String, String> search) {
        String keyword = String.valueOf(search.get("keyword"));
        String startDate = String.valueOf(search.get("startDate"));
        String endDate = String.valueOf(search.get("endDate"));
        LambdaQueryWrapper<SysRole> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(startDate) && !startDate.equals("null")) {
            lambdaQueryWrapper.between(SysRole::getCreateTime, startDate, endDate);
        }
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysRole::getRoleName, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysRole::getId, keyword);
        }
        return this.baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<String> getPermission(String id) {
        LambdaQueryWrapper<SysRolePermission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SysRolePermission::getRoleId, id);
        List<SysRolePermission> sysRolePermissions = sysRolePermissionService.list(lambdaQueryWrapper);
        List<String> list = sysRolePermissions.stream().map(sysRolePermission -> {
            String menuId = sysRolePermission.getMenuId().toString();
            return menuId;
        }).collect(Collectors.toList());
        return list;
    }
}
