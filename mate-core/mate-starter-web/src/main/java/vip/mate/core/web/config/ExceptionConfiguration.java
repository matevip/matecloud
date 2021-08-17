package vip.mate.core.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.web.handler.BaseExceptionHandler;

/**
 * 统一异常处理配置
 * @author xuzhanfu
 */
@Configuration
@ComponentScan(value="vip.mate.core.web.handler")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-error.yml")
public class ExceptionConfiguration {

    @Bean
    public BaseExceptionHandler baseExceptionHandler(){
        return new BaseExceptionHandler();
    }
}