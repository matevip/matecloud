package vip.mate.core.log.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import vip.mate.core.common.util.AddressUtil;
import vip.mate.core.common.util.RequestHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(vip.mate.core.log.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        Object result = new Object();
        // 打印执行时间
        long startTime = System.nanoTime();
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        // 判断为空则直接跳过执行
        if (ObjectUtils.isEmpty(request)){
            return point.proceed();
        }
        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();

        // 构建成一条长 日志，避免并发下日志错乱
        StringBuilder beforeReqLog = new StringBuilder(300);
        // 日志参数
        List<Object> beforeReqArgs = new ArrayList<>();
        // 打印路由
        beforeReqLog.append("Request Log {}: {}");
        beforeReqArgs.add(requestMethod);
        beforeReqArgs.add(requestUrl);

        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);
            beforeReqLog.append(" {}: {}\n");
            beforeReqArgs.add(headerName);
            beforeReqArgs.add(headerValue);
        }

        String ip = RequestHolder.getHttpServletRequestIpAddress();
        String region = AddressUtil.getCityInfo(ip);
        beforeReqLog.append("IP地址信息：ip {}: 地址 {}");
        beforeReqArgs.add(ip);
        beforeReqArgs.add(region);
        try {
            result = point.proceed();
        } finally {

            beforeReqLog.append("===Result===  {}\n");
            beforeReqArgs.add(result);
            long tookTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
            beforeReqLog.append("<=== {}: {} ({} ms)\n");
            beforeReqArgs.add(requestMethod);
            beforeReqArgs.add(requestUrl);
            beforeReqArgs.add(tookTime);
            log.error(beforeReqLog.toString(), beforeReqArgs.toArray());
        }
        return result;
    }

}
