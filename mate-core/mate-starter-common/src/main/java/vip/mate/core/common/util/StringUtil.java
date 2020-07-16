package vip.mate.core.common.util;

import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {

    public static boolean isBlank(String string) {
       return StringUtils.isEmpty(string) || string.equals("null");
    }

    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
