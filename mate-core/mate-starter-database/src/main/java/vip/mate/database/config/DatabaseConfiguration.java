package vip.mate.database.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.common.factory.YamlPropertySourceFactory;

@Configuration
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-db.yml")
public class DatabaseConfiguration {
}
