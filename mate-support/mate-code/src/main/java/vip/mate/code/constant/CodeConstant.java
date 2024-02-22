package vip.mate.code.constant;

import cn.hutool.core.util.StrUtil;

/**
 * 代码生成常量
 *
 * @author david
 * @since
 */
public class CodeConstant {

    /**
     * 自增
     */
    public static final String TABLE_AUTO_INCREMENT = "auto_increment";

    /**
     * PRI
     */
    public static final String PRI = "PRI";

    /**
     * 是否可空
     */
    public static final String NO = "no";

    /**
     * 是否惟一
     */
    public static final String UNI = "UNI";


    /**
     * 数据库字符串类型
     */
    public static final String[] COLUMN_TYPE_STR = {"char", "varchar", "nvarchar", "varchar2"};

    /**
     * 数据库文本类型
     */
    public static final String[] COLUMN_TYPE_TEXT = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     * 数据库日期类型
     */
    public static final String[] COLUMN_TYPE_DATE = {"datetime", "time", "date", "timestamp"};

    /**
     * 数据库时间类型
     */
    public static final String[] COLUMN_TYPE_TIME = {"datetime", "time", "date", "timestamp"};

    /**
     * 数据库数字类型
     */
    public static final String[] COLUMN_TYPE_NUMBER = {"tinyint", "smallint", "mediumint", "int", "number", "integer"};

    /**
     * 数据库长数字类型
     */
    public static final String[] COLUMN_TYPE_LONG = {"bigint"};

    /**
     * 数据库浮点类型
     */
    public static final String[] COLUMN_TYPE_FLOAT = {"float", "double", "decimal"};

    /**
     * 页面不需要新增字段
     */
    public static final String[] COLUMN_NAME_NOT_INSERT = {"id", "createBy", "createTime", "updateBy", "updateTime"};

    /**
     * 页面不需要查看字段
     */
    public static final String[] COLUMN_NAME_NOT_VIEW = {"id", "createBy", "createTime", "updateBy", "updateTime"};

    /**
     * 页面不需要编辑字段
     */
    public static final String[] COLUMN_NAME_NOT_EDIT = {"id", "createBy", "createTime", "updateBy", "updateTime"};

    /**
     * 页面不需要显示的列表字段
     */
    public static final String[] COLUMN_NAME_NOT_LIST = {"id", "createBy", "createTime", "updateBy", "updateTime", "remark"};

    /**
     * 页面不需要查询字段
     */
    public static final String[] COLUMN_NAME_NOT_QUERY = {"id", "sort", "createBy", "createTime", "updateBy", "updateTime", "remark"};

    /**
     * 页面不需要导入字段
     */
    public static final String[] COLUMN_NAME_NOT_IMPORT = {"id", "createBy", "createTime", "updateBy", "updateTime"};

    /**
     * 页面不需要导出字段
     */
    public static final String[] COLUMN_NAME_NOT_EXPORT = {"id", "sort", "createBy", "updateBy"};

    /**
     * 后端base基类字段
     */
    public static final String[] BASE_ENTITY = {"id", "name", "status", "sort", "remark", "createBy", "createTime", "updateBy", "updateTime", "isDelete"};

    /**
     * 后端tree基类字段
     */
    public static final String[] TREE_ENTITY = {"parentId", "ancestors"};

    /**
     * 后端sub基类字段
     */
    public static final String[] SUB_ENTITY = {};

    /**
     * 后端tenant字段
     */
    public static final String[] TENANT_ENTITY = {"tenantId"};

    /**
     * 前端base基类字段
     */
    public static final String[] BASE_FRONT_ENTITY = {"createBy", "createName", "createTime", "updateBy", "updateName", "updateTime", "delFlag"};

    /**
     * 前端tree基类字段
     */
    public static final String[] TREE_FRONT_ENTITY = {"parentId", "ancestors"};

    /**
     * 前端sub基类字段
     */
    public static final String[] SUB_FRONT_ENTITY = {};

    /**
     * 必定隐藏字段（前后端均隐藏）
     */
    public static final String[] COLUMN_MUST_HIDE = {"is_delete", "tenantId"};

    /**
     * 字典名称转换移除匹配字段
     */
    public static final String[] DICT_TYPE_REMOVE = {"sys_", "te_", "gen_"};

    /**
     * 字典名称结尾字段
     */
    public static final String DICT_NAME_START = "Dic";

    /**
     * 字典名称结尾字段
     */
    public static final String DICT_NAME_END = "Options";

    /**
     * 校验内容
     */
    public enum GenCheck {

        NORMAL_DISABLE("（0正常 1停用）"),
        SHOW_HIDE("（0显示 1隐藏）"),
        YES_NO("（Y是 N否）");

        private final String info;

        GenCheck(String info) {
            this.info = info;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 生成字段
     */
    public enum CodeField {

        ID("id", "主键字段", "id"),
        NAME("name", "名称字段", "name"),
        STATUS("status", "状态字段", "status"),
        SORT("sort", "序号字段", "sort"),
        TYPE("type", "类型字段", "type"),
        SEX("sex", "性别字段", "sex"),
        PARENT_ID("parentId", "树父编码字段", "parent_id"),
        ANCESTORS("ancestors", "祖籍列表字段", "ancestors"),
        LOGO("logo", "logo字段", "logo"),
        IMAGE("image", "图片字段", "image"),
        FILE("file", "文件字段", "file"),
        COMMENT("comment", "注释字段", "comment"),
        REMARK("remark", "备注字段", "remark");

        private final String code;
        private final String info;
        private final String key;

        CodeField(String code, String info, String key) {
            this.code = code;
            this.info = info;
            this.key = key;
        }

        public static CodeField getValue(String code) {
            for (CodeField one : values()) {
                if (StrUtil.equals(code, one.getCode())) {
                    return one;
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }

        public String getKey() {
            return key;
        }
    }

    /**
     * 表模板类型
     */
    public enum TemplateType {

        BASE("base", "单表"),
        TREE("tree", "树表"),
        SUB_BASE("subBase", "主子表"),
        SUB_TREE("subTree", "主子树表"),
        MERGE("merge", "关联表");

        private final String code;
        private final String info;

        TemplateType(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public static TemplateType getValue(String code) {
            for (TemplateType one : values()) {
                if (StrUtil.equals(code, one.getCode())) {
                    return one;
                }
            }
            return null;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 字段Java类型
     */
    public enum JavaType {

        STRING("String", "String"),
        INTEGER("Integer", "Integer"),
        LONG("Long", "Long"),
        DOUBLE("Double", "Double"),
        BOOLEAN("Boolean", "Boolean"),
        BIG_DECIMAL("BigDecimal", "BigDecimal"),
        DATE("Date", "Date");

        private final String code;
        private final String info;

        JavaType(String code, String info) {
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

    /**
     * 字段查询类型
     */
    public enum QueryType {

        EQ("eq", "="),
        NE("ne", "!="),
        GT("gt", ">"),
        GE("ge", ">="),
        LT("lt", "<"),
        LE("le", "<="),
        LIKE("like", "like"),
        NOT_LIKE("notLike", "not like"),
        LIKE_LEFT("likeLeft", "like left"),
        LIKE_RIGHT("likeRight", "like right"),
        BETWEEN("between", "between"),
        NOT_BETWEEN("notBetween", "not between"),
        IS_NULL("isNull", "is null"),
        IS_NOT_NULL("isNotNull", "is not null");

        private final String code;
        private final String info;

        QueryType(String code, String info) {
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

    /**
     * 字段显示类型
     */
    public enum DisplayType {

        INPUT("Input", "文本框"),
        INPUT_PASSWORD("InputPassword", "密码框"),
        INPUT_NUMBER("InputNumber", "数字输入框"),
        INPUT_TEXTAREA("InputTextArea", "文本域"),
        SELECT("Select", "下拉框"),
        TREE_SELECT("TreeSelect", "树型下拉框"),
        CHECKBOX_GROUP("CheckboxGroup", "单选框"),
        RADIO_BUTTON_GROUP("RadioButtonGroup", "按钮风格单选框"),
        RADIO_GROUP("RadioGroup", "复选框"),
        DATE_PICKER("DatePicker", "日期控件"),
        TIME_PICKER("TimePicker", "时间控件"),
        IMAGE_UPLOAD("ImageUpload", "图片上传"),
        FILE_UPLOAD("Upload", "文件上传"),
        TINYMCE("tinymce", "富文本控件"),
        MARKDOWN("markdown", "MarkDown控件");

        private final String code;
        private final String info;

        DisplayType(String code, String info) {
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

    /**
     * 源策略模式
     */
    public enum SourceMode {

        ISOLATE("ISOLATE", "策略源"),
        MASTER("MASTER", "主数据源");

        private final String code;
        private final String info;

        SourceMode(String code, String info) {
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
