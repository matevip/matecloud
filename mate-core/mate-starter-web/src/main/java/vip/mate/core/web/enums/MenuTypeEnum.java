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

    DIR("0", "目录"),
    MENU("1", "菜单"),
    BUTTON("2", "按钮");

    private final String code;

    private final String message;
}
