package vip.mate.core.log.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.common.util.*;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.event.LogEvent;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 日志拦截器
 * @author pangu
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private final ApplicationContext applicationContext;

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    public LogAspect(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Pointcut("@annotation(vip.mate.core.log.annotation.Log)")
    public void pointcut() {
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        Object result = new Object();

        //　获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();
        // 判断为空则直接跳过执行
        if (ObjectUtils.isEmpty(request)){
            return point.proceed();
        }
        //　获取注解里的value值
        Method targetMethod = resolveMethod(point);
        Log logAnn = targetMethod.getAnnotation(Log.class);
        // 打印执行时间
        long startTime = System.nanoTime();
        // 请求方法
        String method = request.getMethod();
        String url = request.getRequestURI();

        // 获取IP和地区
        String ip = RequestHolder.getHttpServletRequestIpAddress();
        String region = IPUtil.getCityInfo(ip);

        //获取请求参数
        //Map<String, Object> paramMap = logIngArgs(point);
        // 参数
        Object[] args = point.getArgs();
        String requestParam = getArgs(args, request);

        // 计算耗时
        long tookTime = 0L;
        try {
            result = point.proceed();
        } finally {
            tookTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        }
        //　如果是登录请求，则不获取用户信息
        String userName = null;
        if (!url.contains("oauth") && !(url.contains("code"))){
            //判断header是否存在，存在则获取用户名
            if (StringUtil.isNotBlank(SecurityUtil.getHeaderToken(request))) {
                userName = SecurityUtil.getUsername(request).getAccount();
            }
        }
        //　封装SysLog
        CommonLog commonLog = new CommonLog();
        commonLog.setIp(ip)
        .setCreateBy(userName)
        .setMethod(method)
        .setUrl(url)
        .setOperation(String.valueOf(result))
        .setLocation(StringUtils.isEmpty(region) ? "本地" : region)
        .setTraceId(request.getHeader(MateConstant.MATE_TRACE_ID))
        .setExecuteTime(tookTime)
        .setTitle(logAnn.value())
        .setParams(JSON.toJSONString(requestParam));
        log.info("Http Request: {}", JSONObject.toJSONString(commonLog));
        // 发布事件
        applicationContext.publishEvent(new LogEvent(commonLog));

        return result;
    }

    /**
     * 配置异常通知
     *
     * @param point join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint point, Throwable e) {
        // 打印执行时间
        long startTime = System.nanoTime();

        CommonLog commonLog = new CommonLog();

        // 获取IP和地区
        String ip = RequestHolder.getHttpServletRequestIpAddress();
        String region = IPUtil.getCityInfo(ip);


        //　获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();

        // 请求方法
        String method = request.getMethod();
        String url = request.getRequestURI();

        //　获取注解里的value值
        Method targetMethod = resolveMethod((ProceedingJoinPoint) point);
        Log logAnn = targetMethod.getAnnotation(Log.class);

        commonLog.setExecuteTime(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime))
        .setIp(ip)
        .setLocation(region)
        .setMethod(method)
        .setUrl(url)
        .setTraceId(TraceUtil.getTraceId(request))
        .setType("2")
        .setTitle(logAnn.value())
        .setException(ThrowableUtil.getStackTrace(e));
        //设置MDC
        TraceUtil.mdcTraceId(TraceUtil.getTraceId(request));
        // 发布事件
        applicationContext.publishEvent(new LogEvent(commonLog));
        log.info("Error Result: {}", commonLog);
    }

    private Method resolveMethod(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();

        Method method = getDeclaredMethod(targetClass, signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("无法解析目标方法: " + signature.getMethod().getName());
        }
        return method;
    }

    private Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethod(superClass, name, parameterTypes);
            }
        }
        return null;
    }

    /**
     * 获取请求参数
     * @param args
     * @param request
     * @return
     */
    private String getArgs(Object[] args, HttpServletRequest request) {
        String strArgs = StringPool.EMPTY;

        try {
            if (!request.getContentType().contains("multipart/form-data")) {
                strArgs = JSONObject.toJSONString(args);
            }
        } catch (Exception e) {
            try {
                strArgs = Arrays.toString(args);
            } catch (Exception ex) {
                log.warn("解析参数异常", ex);
            }
        }
        return strArgs;
    }

    /**
     * 获取方法参数信息
     *
     * @param method         方法
     * @param parameterIndex 参数序号
     * @return {MethodParameter}
     */
    public static MethodParameter getMethodParameter(Method method, int parameterIndex) {
        MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
        methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
        return methodParameter;
    }
}
