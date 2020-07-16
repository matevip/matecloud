package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysUser;
import vip.mate.system.mapper.SysUserMapper;
import vip.mate.system.poi.SysUserPOI;
import vip.mate.system.service.ISysDepartService;
import vip.mate.system.service.ISysDictService;
import vip.mate.system.service.ISysRoleService;
import vip.mate.system.service.ISysUserService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final ISysDepartService sysDepartService;
    private final ISysDictService dictService;
    private final ISysRoleService sysRoleService;

    @Override
    public boolean status(String ids, String status) {
        Collection<? extends Serializable> collection = CollectionUtil.stringToCollection(ids);

        for (Object id: collection) {
            SysUser sysUser = this.baseMapper.selectById(CollectionUtil.objectToLong(id, 0L));
            sysUser.setStatus(status);
            this.baseMapper.updateById(sysUser);
        }
        return true;
    }

    @Override
    public IPage<SysUser> listPage(Page page, Search search) {

        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            queryWrapper.between(SysUser::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StringUtil.isNotBlank(search.getKeyword())) {
            queryWrapper.like(SysUser::getName, search.getKeyword());
            queryWrapper.or();
            queryWrapper.like(SysUser::getId, search.getKeyword());
        }
        IPage<SysUser> sysUserIPage = this.baseMapper.selectPage(page, queryWrapper);
        List<SysUser> sysUserList = sysUserIPage.getRecords().stream().map(sysUser -> {
            sysUser.setDepartName(sysDepartService.getById(sysUser.getDepartId()).getName());
            sysUser.setStatusName(dictService.getValue("status", sysUser.getStatus()).getData());
            sysUser.setRoleName(sysRoleService.getById(sysUser.getRoleId()).getRoleName());
            return sysUser;
        }).collect(Collectors.toList());
        sysUserIPage.setRecords(sysUserList);
        return sysUserIPage;
    }

    public List<SysUserPOI> export() {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getIsDeleted, "0");
        List<SysUser> sysUsers = this.baseMapper.selectList(queryWrapper);
        return sysUsers.stream().map(sysUser -> {
           SysUserPOI sysUserPOI = new SysUserPOI();
           BeanUtils.copyProperties(sysUser, sysUserPOI);
           sysUserPOI.setDepartName(sysDepartService.getById(sysUser.getDepartId()).getName());
           sysUserPOI.setRoleName(sysRoleService.getById(sysUser.getRoleId()).getRoleName());
           sysUserPOI.setStatusName(dictService.getValue("status", sysUser.getStatus()).getData());
           return sysUserPOI;
        }).collect(Collectors.toList());
    }
}
