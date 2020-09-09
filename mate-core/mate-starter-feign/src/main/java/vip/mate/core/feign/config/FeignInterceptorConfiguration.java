package vip.mate.core.feign.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.constant.TenantConstant;
import vip.mate.core.common.context.TenantContextHolder;
import vip.mate.core.common.util.StringUtil;

/**
 * feign拦截器
 * @author pangu
 * @date 2020-9-9
 */
@Slf4j
public class FeignInterceptorConfiguration {

    /**
     * 使用feign client发送请求时，传递tenantId和traceId
     * @return
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            //传递tenantId
            String tenantId = TenantContextHolder.getTenantId();
//            log.error("----------------------------FeignInterceptorConfiguration: tenantId: {}", tenantId);
            if (StringUtil.isNotBlank(tenantId)) {
                requestTemplate.header(TenantConstant.MATE_TENANT_ID, tenantId);
            }
            //传递日志traceId
            String traceId = MDC.get(MateConstant.LOG_TRACE_ID);
//            log.error("----------------------------FeignInterceptorConfiguration: traceId: {}", traceId);
            if (StringUtil.isNotBlank(traceId)) {
                requestTemplate.header(MateConstant.MATE_TRACE_ID, traceId);
            }
        };
    }
}
