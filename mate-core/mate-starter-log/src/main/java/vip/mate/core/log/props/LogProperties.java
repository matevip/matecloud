package vip.mate.core.log.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置
 * @author pangu
 * @date 2020-9-5
 */
@Getter
@Setter
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "mate.kafka";

    /**
     * 是否启用
     */
    private Boolean enable = false;

    /**
     * 记录日志类型
     */
    private LogType logType = LogType.DB;

}
