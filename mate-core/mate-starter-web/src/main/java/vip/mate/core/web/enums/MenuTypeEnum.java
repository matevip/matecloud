package vip.mate.core.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 *
 * @author aristotle
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    /**
     * 目录
     */
    DIR("0", "目录"),
    /**
     * 菜单
     */
    MENU("1", "菜单"),
    /**
     * 按钮
     */
    BUTTON("2", "按钮");

    private final String code;

    private final String message;
}
