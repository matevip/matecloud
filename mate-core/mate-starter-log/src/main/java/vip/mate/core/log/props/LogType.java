package vip.mate.core.log.props;

/**
 * 记录日志类型
 * @author xuzhanfu
 * @date 2020-9-5
 */
public enum LogType {

    /**
     * 记录日志到本地
     */
    LOGGER,
    /**
     * 记录日志到数据库
     */
    DB,
    /**
     * 记录日志到KAFKA
     */
    KAFKA,
    ;

}
