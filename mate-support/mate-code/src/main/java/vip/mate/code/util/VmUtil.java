package vip.mate.code.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import vip.mate.code.entity.Column;
import vip.mate.code.entity.Table;

import java.util.List;

/**
 * 模板工具类
 *
 * @author david
 * @since 2022-3-29
 */
public class VmUtil {

    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * 隐藏字段数组
     */
    private static final String HIDE = "hide";

    /**
     * 覆写字段数组
     */
    private static final String COVER = "cover";

    public static VelocityContext setupContext(Table table, List<Column> columns) {
        String businessName = table.getBusinessName();
        String packageName = table.getPackageName();
        JSONObject optionsObj = JSONObject.parseObject(table.getOptions());
        VelocityContext velocityContext = new VelocityContext();
        // 模板类型
        velocityContext.put("tplCategory", table.getTemplate());
        // 数据库表名
        velocityContext.put("tableName", table.getName());
        // 生成功能名
        velocityContext.put("functionName", table.getFunctionName());
        // 实体类名称（首字母大写）
        velocityContext.put("ClassName",table.getClassName());
        // 实体类名称（首字母小写）
        velocityContext.put("className", StrUtil.lowerFirst(table.getClassName()));
        // 实体类名称（首字母大写/无前缀）
        velocityContext.put("ClassNameNoPrefix", table.getClassName().replaceFirst(table.getPrefix(),""));
        // 实体类名称（首字母小写/无前缀）
        velocityContext.put("classNameNoPrefix", StrUtil.lowerFirst(velocityContext.get("ClassNameNoPrefix").toString()));
        // 生成模块名
        velocityContext.put("moduleName", table.getModuleName());
        // 生成作者名
        velocityContext.put("authorityName", table.getAuthorityName());
        // 生成业务名（首字母大写）
        velocityContext.put("BusinessName", StrUtil.upperFirst(businessName));
        // 生成业务名（首字母小写）
        velocityContext.put("businessName", businessName);
        // 生成业务名（字母全大写）
        velocityContext.put("BUSINESSName", StringUtils.upperCase(businessName));
        // 生成包路径前缀
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        // 生成包路径
        velocityContext.put("packageName", packageName);
        // 作者
        velocityContext.put("author", table.getAuthor());
        // 字段集合
        velocityContext.put("columns", columns);
        // 业务表信息
        velocityContext.put("table", table);
        return velocityContext;
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(StrUtil.DOT);
        return StringUtils.substring(packageName, 0, lastIndex);
    }

}
