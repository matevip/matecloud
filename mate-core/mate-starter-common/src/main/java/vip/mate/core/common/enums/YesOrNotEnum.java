package vip.mate.core.common.enums;

import lombok.Getter;

/**
 * 是或否的枚举，一般用在数据库字段，例如del_flag字段，char(1)，填写Y或N
 *
 * @author aaronuu
 */
@Getter
public enum YesOrNotEnum {

    /**
     * 是
     */
    Y("Y", "是"),

    /**
     * 否
     */
    N("N", "否");

    private final String code;

    private final String message;

    YesOrNotEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
