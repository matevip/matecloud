package vip.mate.core.common.enums;

import lombok.Getter;

/**
 * 性别的枚举
 *
 * @author aaronuu
 */
@Getter
public enum SexEnum {

    /**
     * 男
     */
    M("M", "男"),

    /**
     * 女
     */
    F("F", "女");

    private final String code;

    private final String message;

    SexEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取枚举
     */
    public static SexEnum codeToEnum(String code) {
        if (null != code) {
            for (SexEnum e : SexEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }

    /**
     * 编码转化成中文含义
     */
    public static String codeToMessage(String code) {
        if (null != code) {
            for (SexEnum e : SexEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.getMessage();
                }
            }
        }
        return "未知";
    }

}
