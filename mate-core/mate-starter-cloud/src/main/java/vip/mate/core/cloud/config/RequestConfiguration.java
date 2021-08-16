package vip.mate.core.cloud.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.cloud.filter.TraceFilter;
import vip.mate.core.cloud.props.MateRequestProperties;

/**
 * 请求配置，包括tracId和其他网络请求
 * @author pangu
 */
@Configuration
@EnableConfigurationProperties(MateRequestProperties.class)
public class RequestConfiguration {

//    @Bean
//    public TenantContextHolderFilter tenantContextHolderFilter() {
//        return new TenantContextHolderFilter();
//    }

    @Bean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

}
