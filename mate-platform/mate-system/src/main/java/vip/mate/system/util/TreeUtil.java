package vip.mate.system.util;

import org.springframework.beans.BeanUtils;
import vip.mate.core.web.enums.MenuTypeEnum;
import vip.mate.system.entity.MenuMeta;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.vo.SysMenuVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 树型工具类
 *
 * @author pangu
 */
public class TreeUtil {

    /**
     * 对象转树节点
     *
     * @param sysMenus 系统菜单
     * @return List
     */
    public static List<SysMenuVO> buildTree(List<SysMenu> sysMenus) {
        List<SysMenuVO> trees = new ArrayList<>();
        sysMenus.forEach(sysMenu -> {
            SysMenuVO tree = new SysMenuVO();
            BeanUtils.copyProperties(sysMenu, tree);
            tree.setHidden("1".equals(sysMenu.getHidden()));
            MenuMeta meta = new MenuMeta();
            meta.setIcon(sysMenu.getIcon());
            meta.setTitle(sysMenu.getName());
            // 只有当菜单类型为目录的时候，如果是顶级，则强制修改为Layout
            if (sysMenu.getParentId() == -1L && MenuTypeEnum.DIR.getCode().equals(sysMenu.getType())) {
                tree.setComponent("Layout");
                tree.setRedirect("noRedirect");
                tree.setAlwaysShow(true);
            }
            tree.setMeta(meta);
            if (MenuTypeEnum.DIR.getCode().equals(sysMenu.getType())) {
                tree.setTypeName(MenuTypeEnum.DIR.getMessage());
            } else if (MenuTypeEnum.MENU.getCode().equals(sysMenu.getType())) {
                tree.setTypeName(MenuTypeEnum.MENU.getMessage());
            } else if (MenuTypeEnum.BUTTON.getCode().equals(sysMenu.getType())) {
                tree.setTypeName(MenuTypeEnum.BUTTON.getMessage());
            }
            trees.add(tree);
        });
        return trees;
    }
}
