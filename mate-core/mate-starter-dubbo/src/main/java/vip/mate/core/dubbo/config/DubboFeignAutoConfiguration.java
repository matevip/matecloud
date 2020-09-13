package vip.mate.core.dubbo.config;

import feign.Feign;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.AbstractConfig;
import org.apache.dubbo.config.spring.beans.factory.annotation.DubboFeignBuilder;
import org.apache.dubbo.config.spring.beans.factory.annotation.DubboFeignProviderBeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.apache.dubbo.spring.boot.util.DubboUtils.*;

/**
 * Dubbo配置
 *
 * @author pangu
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = DUBBO_PREFIX, name = "enabled", matchIfMissing = true, havingValue = "true")
@ConditionalOnClass(AbstractConfig.class)
public class DubboFeignAutoConfiguration {

    @ConditionalOnProperty(prefix = DUBBO_SCAN_PREFIX, name = BASE_PACKAGES_PROPERTY_NAME)
    @ConditionalOnClass(ConfigurationPropertySources.class)
    @Bean
    public DubboFeignProviderBeanPostProcessor dubboFeignProviderBeanPostProcessor(Environment environment) {
        Set<String> packagesToScan = environment.getProperty(DUBBO_SCAN_PREFIX + BASE_PACKAGES_PROPERTY_NAME, Set.class, emptySet());
        return new DubboFeignProviderBeanPostProcessor(packagesToScan);
    }

    @Primary
    @Bean
    public Feign.Builder feignDubboBuilder() {
        return new DubboFeignBuilder();
    }
}
