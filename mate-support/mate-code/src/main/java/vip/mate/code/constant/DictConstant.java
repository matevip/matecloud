package vip.mate.code.constant;

/**
 * 字典通用常量
 *
 * @author xueyi
 */
public class DictConstant {

    /** 字典类型 */
    public enum DictType {

        SYS_NORMAL_DISABLE("sys_normal_disable", "系统开关列表"),
        SYS_SHOW_HIDE("sys_show_hide", "常规：显隐列表"),
        SYS_COMMON_PRIVATE("sys_common_private", "常规：公共私有列表"),
        SYS_YES_NO("sys_yes_no", "常规：是否列表"),
        SYS_USER_SEX("sys_user_sex", "用户性别列表");

        private final String code;
        private final String info;

        DictType(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /** 常规：是否列表（Y是 N否） */
    public enum DicYesNo {

        YES("Y", "是"),
        NO("N", "否");

        private final String code;
        private final String info;

        DicYesNo(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /** 常规：显隐列表（0显示 1隐藏） */
    public enum DicShowHide {

        SHOW("0", "显示"),
        HIDE("1", "隐藏");

        private final String code;
        private final String info;

        DicShowHide(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /** 常规：公共私有列表（0公共 1私有） */
    public enum DicCommonPrivate {

        COMMON("0", "公共"),
        PRIVATE("1", "私有");

        private final String code;
        private final String info;

        DicCommonPrivate(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /** 常规：状态列表（0正常 1失败） */
    public enum DicStatus {

        NORMAL("0", "正常"),
        FAIL("1", "失败");

        private final String code;
        private final String info;

        DicStatus(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}
