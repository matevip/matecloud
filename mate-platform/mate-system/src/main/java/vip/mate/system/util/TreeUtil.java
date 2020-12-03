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
	 * 数组转树形结构
	 *
	 * @param sysMenus 菜单列表
	 * @param root     根节点
	 * @return List
	 */
	public static List<SysMenuVO> list2Tree(List<SysMenu> sysMenus, Long root) {
		// 普通对象转树节点
		List<SysMenuVO> sysMenuVOList = buildTree(sysMenus);
		List<SysMenuVO> trees = new ArrayList<>();
		for (SysMenuVO tree : sysMenuVOList) {
			if (root.equals(tree.getParentId())) {
				trees.add(tree);
			}

			for (SysMenuVO t : sysMenuVOList) {
				if (tree.getId().equals(t.getParentId())) {
					if (tree.getChildren() == null) {
						tree.setChildren(new ArrayList<SysMenuVO>());
					}
					tree.addChildren(t);
				}
			}
		}
		return trees;
	}

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
			tree.setComponent(sysMenu.getPath());
			if (sysMenu.getParentId() == -1L) {
				tree.setComponent("Layout");
				tree.setRedirect("noRedirect");
				tree.setAlwaysShow(true);
			}
			tree.setMeta(meta);
			if ("0".equals(sysMenu.getType())) {
				tree.setTypeName(MenuTypeEnum.DIR.getMessage());
			} else if ("1".equals(sysMenu.getType())) {
				tree.setTypeName(MenuTypeEnum.MENU.getMessage());
			} else if ("2".equals(sysMenu.getType())) {
				tree.setTypeName(MenuTypeEnum.BUTTON.getMessage());
			}
			trees.add(tree);
		});
		return trees;
	}
}
