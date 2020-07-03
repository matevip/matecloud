package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.web.enums.MenuTypeEnum;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.util.TreeUtil;
import vip.mate.system.vo.SysMenuVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
//        //2. 去掉非菜单类型
//        List<SysMenu> sysMenuList = menus.stream()
//                .filter(sysMenu -> MenuTypeEnum.MENU.getCode().equals(sysMenu.getType()) ||
//                        MenuTypeEnum.DIR.getCode().equals(sysMenu.getType()) )
//                .sorted(Comparator.comparingInt(SysMenu::getSort))
//                .collect(Collectors.toList());
        //3. 生成菜单树
        return TreeUtil.list2Tree(menus, MateConstant.TREE_ROOT);
    }

    @Override
    public List<SysMenu> searchList(Map<String, Object> search) {
        String keyword = String.valueOf(search.get("keyword"));
        String startDate = String.valueOf(search.get("startDate"));
        String endDate = String.valueOf(search.get("endDate"));
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(startDate) && !startDate.equals("null")) {
            lambdaQueryWrapper.between(SysMenu::getCreateTime, startDate, endDate);
        }
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysMenu::getName, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysMenu::getId, keyword);
        }
        return this.baseMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public boolean saveAll(SysMenu sysMenu) {
        if (sysMenu.getType().equals("0")) {
            sysMenu.setParentId(-1L);
        }
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
}
