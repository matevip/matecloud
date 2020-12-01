package vip.mate.core.seata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;

/**
 * Seata配置
 *
 * @author pangu
 * @since 2.1.8-SNAPSHOT
 */
@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-seata.yml")
public class SeataConfiguration {
}
