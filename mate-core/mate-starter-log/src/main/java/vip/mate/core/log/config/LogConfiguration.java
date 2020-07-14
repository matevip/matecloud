package vip.mate.core.log.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.log.aspect.LogAspect;

@Configuration
@ConditionalOnWebApplication
public class LogConfiguration {

    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }


}
