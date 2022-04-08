package vip.mate.code.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import vip.mate.code.entity.Column;
import vip.mate.code.entity.Table;
import vip.mate.core.common.util.StringPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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

    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, StringPool.UTF_8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
        velocityContext.put("ClassName", table.getClassName());
        // 实体类名称（首字母小写）
        velocityContext.put("className", StrUtil.lowerFirst(table.getClassName()));
        // 实体类名称（首字母大写/无前缀）
        velocityContext.put("ClassNameNoPrefix", table.getClassName().replaceFirst(table.getPrefix(), ""));
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

    /**
     * 获取模板信息
     *
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory) {
        List<String> templates = new ArrayList<>();
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/java/entity.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/mapper.xml.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        // 待扩展
        return templates;
    }

}
