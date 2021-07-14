package vip.mate.core.web.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.*;

/**
 * Collection工具类
 *
 * @author pangu
 */
public class CollectionUtil {

    /**
     * 将以“,”分隔的字符串转成为Collection
     *
     * @param str 字符串
     * @return Collection
     */
    public static Collection<? extends Serializable> stringToCollection(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            String[] strArray = str.split(",");
            final Long[] longs = new Long[strArray.length];
            for (int i = 0; i < strArray.length; i++) {
                longs[i] = strToLong(strArray[i], 0L);
            }
            return arrayToCollection(longs);
        }
    }

    /**
     * 将字组转换成Collection
     *
     * @param longArray Long数组
     * @return Collection
     */
    public static Collection<? extends Serializable> arrayToCollection(Long[] longArray) {
        Collection<? extends Serializable> collection = new ArrayList<>();
        CollectionUtils.addAll(collection, longArray);
        return collection;
    }

    /**
     * 字符串转换为long
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static long strToLong(@Nullable final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 字符串转换为long
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static long objectToLong(@Nullable final Object str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.valueOf(String.valueOf(str));
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }
}
