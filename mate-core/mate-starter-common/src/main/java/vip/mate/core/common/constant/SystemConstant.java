package vip.mate.core.common.constant;

import lombok.experimental.UtilityClass;

/**
 * 系统管理缓存name
 *
 * @author pangu
 * 2020-7-4
 */
@UtilityClass
public class SystemConstant {

    public static final String SYS_USER_CACHE = "sys-user-cache-";

    public static final String SYS_ROLE_CACHE = "sys-role-cache-";

    public static final String SYS_DEPART_CACHE = "sys-depart-cache-";

    public static final String SYS_MENU_CACHE = "sys-menu-cache-";

    public static final String SYS_DICT_CACHE = "sys-dict-cache-";

    /**
     * 角色权限默认值
     */
    public static final String ROLE_DEFAULT_ID = "0";
    /**
     * 角色权限默认名称
     */
    public static final String ROLE_DEFAULT_VALUE = "角色未授权";
}
