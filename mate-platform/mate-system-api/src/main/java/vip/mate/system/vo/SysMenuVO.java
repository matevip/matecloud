package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vip.mate.system.entity.MenuMeta;
import vip.mate.system.entity.SysMenu;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuVO implements Serializable {

    private static final long serialVersionUID = -8036122418979736148L;

    /**
     * 子节点
     */
    private List<SysMenuVO> children;

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 菜单标题
     */
    private String name;

    /**
     * 菜单权限
     */
    private String permission;

    /**
     * 路径
     */
    private String path;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序值
     */
    private Integer sort;

    private MenuMeta meta;

    private String component;

    private Boolean hidden=false;

    private String redirect;

    private Boolean alwaysShow = false;

    public void addChildren(SysMenuVO tree) {
        this.children.add(tree);
    }

}
