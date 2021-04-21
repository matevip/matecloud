package vip.mate.core.cloud.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vip.mate.core.common.constant.TenantConstant;
import vip.mate.core.common.context.TenantContextHolder;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.core.common.util.StringUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 租户上下文过滤器
 * @author pangu
 * @date 2020-9-7
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantContextHolderFilter extends GenericFilterBean {
    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            //优先取请求参数中的tenantId值
            String tenantId = request.getHeader(TenantConstant.MATE_TENANT_ID);
            if (StringUtil.isBlank(tenantId)) {
                String token = SecurityUtil.getHeaderToken(request);
                if (StringUtil.isNotBlank(token)) {
                    //取token中的tenantId值
                    LoginUser loginUser = SecurityUtil.getUsername(request);
                    if (loginUser != null) {
                        tenantId = String.valueOf(loginUser.getTenantId());
                    }
                }
            }
            log.info("获取到的租户ID为:{}", tenantId);
            if (StringUtil.isNotBlank(tenantId)) {
                TenantContextHolder.setTenantId(tenantId);
            } else {
                if (StringUtil.isBlank(TenantContextHolder.getTenantId())) {
                    TenantContextHolder.setTenantId(TenantConstant.TENANT_ID_DEFAULT);
                }
            }
            filterChain.doFilter(request, response);
        } finally {
            TenantContextHolder.clear();
        }
    }
}
