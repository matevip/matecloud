package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.web.tree.ForestNodeMerger;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.poi.SysMenuPOI;
import vip.mate.system.service.ISysMenuService;
import vip.mate.system.util.TreeUtil;
import vip.mate.system.vo.SysMenuVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenuVO> routes(String roleId) {
        //1. 获取用户的菜单列表，待扩展
        List<SysMenu> menus = this.baseMapper.routes(roleId);
        //2. 生成菜单树
        return ForestNodeMerger.merge(TreeUtil.buildTree(menus));
    }

    @Override
    public List<SysMenu> searchList(Search search) {
        LambdaQueryWrapper<SysMenu> lambda = Wrappers.<SysMenu>query().lambda();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            lambda.between(SysMenu::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StringUtil.isNotBlank(search.getKeyword())) {
            lambda.like(SysMenu::getName, search.getKeyword()).or().like(SysMenu::getId, search.getKeyword());
        }
        lambda.orderByAsc(SysMenu::getSort);
        return this.baseMapper.selectList(lambda);
    }

    @Override
    public boolean saveAll(SysMenu sysMenu) {
        return saveOrUpdate(sysMenu);
    }

    @Override
    public boolean status(String ids, String status) {
        Collection<? extends Serializable> collection = CollectionUtil.stringToCollection(ids);

        for (Object id: collection){
            SysMenu sysMenu = this.baseMapper.selectById(CollectionUtil.objectToLong(id, 0L));
            sysMenu.setStatus(status);
            this.baseMapper.updateById(sysMenu);
        }
        return true;
    }

    @Override
    public List<SysMenuPOI> export() {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getIsDeleted, 0);
        queryWrapper.orderByAsc(SysMenu::getId);
        List<SysMenu> sysMenus = this.baseMapper.selectList(queryWrapper);
        return sysMenus.stream().map(sysMenu -> {
            SysMenuPOI sysMenuPOI = new SysMenuPOI();
            BeanUtils.copyProperties(sysMenu, sysMenuPOI);
            return sysMenuPOI;
        }).collect(Collectors.toList());
    }
}
