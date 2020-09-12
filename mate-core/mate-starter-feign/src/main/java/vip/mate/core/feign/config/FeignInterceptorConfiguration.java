package vip.mate.core.feign.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.constant.TenantConstant;
import vip.mate.core.common.context.TenantContextHolder;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.common.util.TraceUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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
            if (StringUtil.isNotBlank(tenantId)) {
                requestTemplate.header(TenantConstant.MATE_TENANT_ID, tenantId);
            }
            //传递日志traceId
            String traceId = MDC.get(MateConstant.LOG_TRACE_ID);
            if (StringUtil.isBlank(traceId)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    Enumeration<String> headerNames = request.getHeaderNames();
                    if (headerNames != null) {
                        String headerName = null;
                        while (headerNames.hasMoreElements()) {
                            headerName = headerNames.nextElement();
                            if (headerName.equalsIgnoreCase(MateConstant.MATE_TRACE_ID)) {
                                traceId = request.getHeader(headerName);
                                requestTemplate.header(MateConstant.MATE_TRACE_ID, traceId);
                                TraceUtil.mdcTraceId(traceId);
                            }
                        }
                    }
                }
            } else {
                if (StringUtil.isNotBlank(traceId)) {
                    requestTemplate.header(MateConstant.MATE_TRACE_ID, traceId);
                }
            }
        };
    }
}
