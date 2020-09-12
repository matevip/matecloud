package vip.mate.system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import vip.mate.core.common.exception.PreviewException;
import vip.mate.core.common.util.RequestHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * 演示环境拦截器
 * @author pangu
 */
@Slf4j
@Aspect
@Component
public class PreviewAspect {

    @Value("${mate.preview.enable}")
    private boolean isPreview = false;

    @Around(
            "execution(static vip.mate.core.common.api.Result *(..)) || " +
                    "(@within(org.springframework.stereotype.Controller) || " +
                    "@within(org.springframework.web.bind.annotation.RestController))"
    )
    public Object aroundApi(ProceedingJoinPoint point) throws Throwable {
        //　获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        if (StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.POST.name()) && isPreview
        && !(StringUtils.equalsIgnoreCase(request.getRequestURI(), "/provider/log/save"))) {
            log.error("演示环境不能操作！");
            throw new PreviewException("演示环境不能操作！");
        }
        return point.proceed();
    }
}
