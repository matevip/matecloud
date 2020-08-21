package vip.mate.core.redis.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(MateRedisProperties.PREFIX)
public class MateRedisProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "mate.lettuce.redis";
    /**
     * 是否开启Lettuce
     */
    private Boolean enable = true;
}
