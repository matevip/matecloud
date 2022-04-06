package vip.mate.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 菜单描述
 *
 * @author pangu
 */
@Data
public class MenuMeta implements Serializable {

    private static final long serialVersionUID = -1918771120674335058L;

    private String title;
    private String icon;
    private Boolean breadcrumb = true;
    /**
     * 当前路由不再菜单显示
     */
    private Boolean hideMenu = false;
}
