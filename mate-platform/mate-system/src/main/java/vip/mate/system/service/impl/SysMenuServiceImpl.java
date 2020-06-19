package vip.mate.system.service.impl;

import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.web.enums.MenuTypeEnum;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.util.TreeUtil;
import vip.mate.system.vo.SysMenuVO;

import java.util.Comparator;
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
    public List<SysMenuVO> routes() {
        //1. 获取用户的菜单列表，待扩展
        List<SysMenu> menus = list();
        //2. 去掉非菜单类型
        List<SysMenu> sysMenuList = menus.stream()
                .filter(sysMenu -> MenuTypeEnum.MENU.getCode().equals(sysMenu.getType()))
                .sorted(Comparator.comparingInt(SysMenu::getSort))
                .collect(Collectors.toList());
        //3. 生成菜单树
        return TreeUtil.list2Tree(sysMenuList, MateConstant.TREE_ROOT);
    }
}
