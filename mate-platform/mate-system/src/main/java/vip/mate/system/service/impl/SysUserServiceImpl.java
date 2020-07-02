package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysUser;
import vip.mate.system.mapper.SysUserMapper;
import vip.mate.system.service.ISysDepartService;
import vip.mate.system.service.ISysDictService;
import vip.mate.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.vo.SysUserVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
    public IPage<SysUser> listPage(Map<String, String> query) {
        long current = CollectionUtil.strToLong(query.get("current"), 0L);
        long size = CollectionUtil.strToLong(query.get("size"), 0L);
        IPage<SysUser> page = new Page<>(current, size);

        String keyword = String.valueOf(query.get("keyword"));
        String startDate = String.valueOf(query.get("startDate"));
        String endDate = String.valueOf(query.get("endDate"));
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(startDate) && !startDate.equals("null")) {
            lambdaQueryWrapper.between(SysUser::getCreateTime, startDate, endDate);
        }
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysUser::getName, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysUser::getId, keyword);
        }
        IPage<SysUser> sysUserIPage = this.baseMapper.selectPage(page, lambdaQueryWrapper);
        List<SysUser> sysUserList = sysUserIPage.getRecords().stream().map(sysUser -> {
            sysUser.setDepartName(sysDepartService.getById(sysUser.getDepartId()).getName());
            sysUser.setStatusName(dictService.getValue("status", sysUser.getStatus()).getData());
            return sysUser;
        }).collect(Collectors.toList());
        sysUserIPage.setRecords(sysUserList);

        return sysUserIPage;
    }


}
