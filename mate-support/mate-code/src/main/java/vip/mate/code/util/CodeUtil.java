package vip.mate.code.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.RegExUtils;
import vip.mate.code.config.CodeConfig;
import vip.mate.code.constant.CodeConstant;
import vip.mate.code.constant.DictConstant;
import vip.mate.code.entity.Column;
import vip.mate.code.entity.Table;

import java.util.Arrays;
import java.util.Objects;

/**
 * 代码生成工具类
 *
 * @author david
 * @since 2022-3-28
 */
public class CodeUtil {

    /**
     * 初始化信息表，对数据进行二次封装
     *
     * @param table 信息表对象
     */
    public static void initTableExt(Table table) {
        getRemoveItem(table);
        table.setClassName(CamelUtil.underline2Camel(table.getName(), false));
        table.setBusinessName(getBusinessName(table.getName()));
        table.setFunctionName(replaceText(table.getComment()));
        table.setAuthor(CodeConfig.getAuthor());
        if (StrUtil.isNotBlank(CodeConfig.getUiPath())) {
            table.setUiPath(CodeConfig.getUiPath());
        }
    }

    public static void initColumnField(Column column, Table table) {
        String dataType = column.getType();
        column.setTableId(table.getId().toString());
        column.setCreateBy(table.getCreateBy());
        // 设置 java 字段名
        column.setJavaField(CamelUtil.underline2Camel(column.getName(), false));
        String javaField = column.getJavaField();
        // 设置默认类型
        column.setJavaType(CodeConstant.JavaType.STRING.getCode());
        // 设置默认显示类型
        column.setHtmlType(CodeConstant.DisplayType.INPUT.getCode());
        // 设置默认查询类型（长整型防精度丢失，到前端会自动转成字符串，故使用文本框）
        column.setQueryType(CodeConstant.QueryType.EQ.getCode());

        if (arraysContains(CodeConstant.COLUMN_TYPE_STR, dataType) && !StrUtil.equals(column.getComment(), column.readNameNoSuffix())) {
            column.setHtmlType(CodeConstant.DisplayType.SELECT.getCode());
            if (StrUtil.contains(column.getComment(), CodeConstant.GenCheck.NORMAL_DISABLE.getInfo())) {
                column.setHtmlType(CodeConstant.DisplayType.RADIO_GROUP.getCode());
                column.setDictType(DictConstant.DictType.SYS_NORMAL_DISABLE.getCode());
            } else if (StrUtil.contains(column.getComment(), CodeConstant.GenCheck.SHOW_HIDE.getInfo())) {
                column.setHtmlType(CodeConstant.DisplayType.RADIO_BUTTON_GROUP.getCode());
                column.setDictType(DictConstant.DictType.SYS_SHOW_HIDE.getCode());
            } else if (StrUtil.contains(column.getComment(), CodeConstant.GenCheck.YES_NO.getInfo())) {
                column.setHtmlType(CodeConstant.DisplayType.RADIO_BUTTON_GROUP.getCode());
                column.setDictType(DictConstant.DictType.SYS_YES_NO.getCode());
            }
        } else if (arraysContains(CodeConstant.COLUMN_TYPE_TEXT, dataType)) {
            column.setHtmlType(CodeConstant.DisplayType.INPUT_TEXTAREA.getCode());
        } else if (arraysContains(CodeConstant.COLUMN_TYPE_DATE, dataType)) {
            column.setJavaType(CodeConstant.JavaType.DATE.getCode());
            column.setHtmlType(CodeConstant.DisplayType.DATE_PICKER.getCode());
        } else if (arraysContains(CodeConstant.COLUMN_TYPE_FLOAT, dataType)) {
            column.setHtmlType(CodeConstant.DisplayType.INPUT_NUMBER.getCode());
            column.setJavaType(CodeConstant.JavaType.BIG_DECIMAL.getCode());
        } else if (arraysContains(CodeConstant.COLUMN_TYPE_NUMBER, dataType)) {
            column.setHtmlType(CodeConstant.DisplayType.INPUT_NUMBER.getCode());
            column.setJavaType(CodeConstant.JavaType.INTEGER.getCode());
        } else if (arraysContains(CodeConstant.COLUMN_TYPE_LONG, dataType)) {
            column.setJavaType(CodeConstant.JavaType.LONG.getCode());
        }

        // 插入字段（默认除必须移除的参数外所有字段都需要插入）
        boolean mustCheck = arraysContains(CodeConstant.COLUMN_MUST_HIDE, javaField) || column.getIsPk();
        column.setIsInsert(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_INSERT, javaField) || mustCheck));
        // 查看字段
        column.setIsView(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_VIEW, javaField) || mustCheck));
        // 编辑字段
        column.setIsEdit(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_EDIT, javaField) || mustCheck));
        // 列表字段
        column.setIsList(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_LIST, javaField) || mustCheck));
        // 查询字段
        column.setIsQuery(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_QUERY, javaField) || mustCheck));
        // 导入字段
        column.setIsImport(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_IMPORT, javaField) || mustCheck));
        // 导出字段
        column.setIsExport(!(arraysContains(CodeConstant.COLUMN_NAME_NOT_EXPORT, javaField) || mustCheck));
        // 隐藏字段
        column.setIsHide(arraysContains(CodeConstant.COLUMN_MUST_HIDE, javaField));
        // 覆盖字段（默认无需覆盖字段）
        column.setIsCover(false);
        // 特殊指定
        CodeConstant.CodeField field = CodeConstant.CodeField.getValue(javaField);
        if (ObjectUtil.isNotNull(field)) {
            switch (Objects.requireNonNull(field)) {
                case NAME:
                    column.setQueryType(CodeConstant.QueryType.LIKE.getCode());
                    break;
                case SEX:
                    column.setDictType(DictConstant.DictType.SYS_USER_SEX.getCode());
                    break;
                case LOGO:
                case IMAGE:
                    column.setHtmlType(CodeConstant.DisplayType.IMAGE_UPLOAD.getCode());
                    break;
                case FILE:
                    column.setHtmlType(CodeConstant.DisplayType.FILE_UPLOAD.getCode());
                    break;
                case COMMENT:
                    column.setHtmlType(CodeConstant.DisplayType.TINYMCE.getCode());
                    break;
                case REMARK:
                    column.setHtmlType(CodeConstant.DisplayType.INPUT_TEXTAREA.getCode());
                    break;
            }
        }
        // 最终校验
        basicInitColumn(column);
    }

    /**
     * 最终校验列属性字段
     */
    public static void basicInitColumn(Column column) {
        // 校验是否需要覆盖
        CodeConstant.CodeField field = CodeConstant.CodeField.getValue(column.getJavaField());
        if (ObjectUtil.isNotNull(field)) {
            switch (Objects.requireNonNull(field)) {
                case ID:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.LONG.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.ID.getKey()))) {
                        column.setIsCover(true);
                    }
                    break;
                case NAME:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.STRING.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.NAME.getKey())
                            && StrUtil.equals(column.getQueryType(), CodeConstant.QueryType.LIKE.getCode()))) {
                        column.setIsCover(true);
                    }
                    break;
                case STATUS:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.STRING.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.STATUS.getKey())
                            && StrUtil.contains(column.getComment(), CodeConstant.GenCheck.NORMAL_DISABLE.getInfo()))) {
                        column.setIsCover(true);
                    }
                    break;
                case SORT:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.INTEGER.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.SORT.getKey()))) {
                        column.setIsCover(true);
                    }
                    break;
                case PARENT_ID:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.LONG.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.PARENT_ID.getKey()))) {
                        column.setIsCover(true);
                    }
                    break;
                case ANCESTORS:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.STRING.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.ANCESTORS.getKey()))) {
                        column.setIsCover(true);
                    }
                    break;
                case REMARK:
                    if (!(StrUtil.equals(column.getJavaType(), CodeConstant.JavaType.STRING.getCode())
                            && StrUtil.equals(column.getName(), CodeConstant.CodeField.REMARK.getKey()))) {
                        column.setIsCover(true);
                    }
                    break;
                default:
                    column.setIsCover(false);
            }
        }
        // 校验是否需要隐藏
        boolean isMustHide = StrUtil.equalsAny(column.getName(), CodeConstant.COLUMN_MUST_HIDE);
        if (column.getIsHide() || isMustHide) {
            if (isMustHide) {
                column.setIsHide(true);
                column.setIsCover(false);
            }
            column.setIsView(false);
            column.setIsInsert(false);
            column.setIsEdit(false);
            column.setIsImport(false);
            column.setIsExport(false);
            column.setIsUnique(false);
            column.setIsRequired(false);
            column.setIsList(false);
            column.setIsQuery(false);
        }
    }


    /**
     * 替换前缀、获取包和和模块名
     *
     * @param table 数据表对象
     */
    public static void getRemoveItem(Table table) {
        boolean autoRemovePre = CodeConfig.isAutoRemovePre();
        if (autoRemovePre && CollUtil.isNotEmpty(CodeConfig.getRemoveLists())) {
            for (CodeConfig.RemoveItem removeItem : CodeConfig.removeLists) {
                if (StrUtil.equals(StrUtil.sub(table.getName(),
                        0, removeItem.getPrefix().length()), removeItem.getPrefix())) {
                    table.setPrefix(CamelUtil.underline2Camel(removeItem.getPrefix(), false));
                    table.setPackageName(removeItem.getPackageName());
                    table.setModuleName(getModuleName(removeItem.getPackageName()));
                    table.setAuthorityName(table.getModuleName());
                    return;
                }
            }
        }
    }

    /**
     * 获取模块名
     *
     * @param packageName 包名
     * @return 模块名
     */
    public static String getModuleName(String packageName) {
        int lastIndex = packageName.lastIndexOf(StrUtil.DOT);
        int nameLength = packageName.length();
        return StrUtil.sub(packageName, lastIndex + 1, nameLength);
    }

    /**
     * 获取业务名
     *
     * @param tableName 表名
     * @return 业务名
     */
    public static String getBusinessName(String tableName) {
        int lastIndex = tableName.lastIndexOf(StrUtil.UNDERLINE);
        int nameLength = tableName.length();
        return StrUtil.sub(tableName, lastIndex + 1, nameLength);
    }

    /**
     * 关键字替换
     *
     * @param text 需要被替换的名字
     * @return 替换后的名字
     */
    public static String replaceText(String text) {
        return RegExUtils.replaceAll(text, "(?:信息表|表)", StrUtil.EMPTY);
    }

    /**
     * 检验数组是否包含指定值
     *
     * @param arr         数组
     * @param targetValue 值
     * @return 是否包含
     */
    public static boolean arraysContains(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }
}
