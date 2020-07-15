package vip.mate.core.log.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import vip.mate.core.log.aspect.LogAspect;
import vip.mate.core.log.event.LogListener;
import vip.mate.system.feign.ISysLogProvider;

@EnableAsync
@Configuration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogConfiguration {

    private final ISysLogProvider sysLogProvider;

    @Bean
    public LogListener sysLogListener() {
        return new LogListener(sysLogProvider);
    }

    @Bean
    public LogAspect logAspect(ApplicationContext applicationContext){
        return new LogAspect(applicationContext);
    }


}
