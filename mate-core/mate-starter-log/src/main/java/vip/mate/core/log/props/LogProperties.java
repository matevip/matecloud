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
     * 日志记录的总开关（通过kafka）
     */
    private Boolean enable = false;

}
