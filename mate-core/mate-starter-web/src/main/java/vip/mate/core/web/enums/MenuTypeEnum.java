package vip.mate.core.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 * @author aristotle
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

    MENU("0", "菜单"),
    BUTTON("1","按钮");

    private final String code;

    private final String message;
}
