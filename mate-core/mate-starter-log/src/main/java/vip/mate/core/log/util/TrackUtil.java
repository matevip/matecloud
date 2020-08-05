package vip.mate.core.log.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 日志埋点追踪类
 * @author pangu
 */
@Slf4j
public class TrackUtil {

    private static final String LOG_PATTERN = "{}|{}|{}";

    private TrackUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 格式为：{时间}|{来源}|{对象id}|{类型}|{对象属性(以&分割)}
     * 例子1：2020-07-27 23:37:23|mate-uaa|1|user-login|ip=xxx.xxx.xx&userName=张三&userType=后台管理员
     * 例子2：2020-07-27 23:37:23|mate-system|c0a895e114526786450161001d1ed9|file-upload|fileName=xxx&filePath=xxx
     *
     * @param id      对象id
     * @param type    类型
     * @param message 对象属性
     */
    public static void info(String id, String type, String message) {
        log.info(LOG_PATTERN, id, type, message);
    }

    public static void debug(String id, String type, String message) {
        log.debug(LOG_PATTERN, id, type, message);
    }

}
