package vip.mate.generator.util;

import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * VelocityEngine工厂
 * 
 * @author ruoyi
 */
public class VelocityInitializer
{
    /**
     * 初始化vm方法
     */
    public static void initVelocity()
    {
        Properties p = new Properties();
        try
        {
            // 加载classpath目录下的vm文件
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty("ISO-8859-1", "UTF-8");
            p.setProperty("output.encoding", "UTF-8");
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
