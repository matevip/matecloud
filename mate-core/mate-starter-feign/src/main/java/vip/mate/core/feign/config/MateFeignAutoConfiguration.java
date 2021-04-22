package vip.mate.core.feign.config;

import feign.Feign;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.MateFeignClientsRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import vip.mate.core.feign.endpoint.FeignClientEndpoint;

/**
 * Feign配置类
 *
 * @author xuzhanfu
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(MateFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class MateFeignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public FeignClientEndpoint feignClientEndpoint(ApplicationContext context) {
        return new FeignClientEndpoint(context);
    }

//    @Bean
//    @Primary
//    public MateHystrixTargeter mateFeignTargeter() {
//        return new MateHystrixTargeter();
//    }
}
