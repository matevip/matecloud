package vip.mate.core.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具
 * @author pangu
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
