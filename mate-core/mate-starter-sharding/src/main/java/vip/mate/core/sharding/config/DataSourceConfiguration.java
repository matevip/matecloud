package vip.mate.core.sharding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-sharding-db.yml")
public class DataSourceConfiguration {
}
