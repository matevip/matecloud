package vip.mate.core.cloud.filter;

import com.alibaba.csp.sentinel.util.StringUtil;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;
import vip.mate.core.cloud.props.MateRequestProperties;
import vip.mate.core.common.constant.MateConstant;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志链路追踪过滤器
 * @author pangu
 * @date 2020-9-8
 */
@ConditionalOnClass(Filter.class)
public class TraceFilter extends OncePerRequestFilter {

    @Autowired
    private MateRequestProperties mateRequestProperties;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !mateRequestProperties.getIsTraceId();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String traceId = request.getParameter(MateConstant.MATE_TRACE_ID);
            if (StringUtil.isNotEmpty(traceId)) {
                MDC.put(MateConstant.LOG_TRACE_ID, traceId);
            }
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }

    }
}
