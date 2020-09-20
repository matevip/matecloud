package vip.mate.core.rocketmq.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;

/**
 * RocketMQ配置
 *
 * @author pangu
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-rocketmq.yml")
public class RocketMQConfiguration {
}
