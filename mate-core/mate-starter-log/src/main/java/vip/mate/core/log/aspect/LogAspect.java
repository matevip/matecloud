package vip.mate.core.log.aspect;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.util.ClassUtil;
import vip.mate.core.common.util.IPUtil;
import vip.mate.core.common.util.RequestHolder;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.log.event.LogEvent;
import vip.mate.system.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class LogAspect {

//    @Autowired
//    private ISysLogProvider sysLogProvider;

    private final ApplicationContext applicationContext;

    private static final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    @Pointcut("@annotation(vip.mate.core.log.annotation.Log)")
    public void pointcut() {
    }

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
        Map<String, Object> paramMap = logIngArgs(point);

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
            userName = SecurityUtil.getUsername(request).getAccount();
        }
        //　封装SysLog
        SysLog sysLog = new SysLog();
        sysLog.setIp(ip);
        sysLog.setCreateBy(userName);
        sysLog.setMethod(method);
        sysLog.setUrl(url);
        sysLog.setOperation(JSON.toJSON(result).toString());
        sysLog.setLocation(StringUtils.isEmpty(region)?"本地":region);
        sysLog.setTraceId(request.getHeader(MateConstant.X_REQUEST_ID));
        sysLog.setExecuteTime(BigDecimal.valueOf(tookTime));
        sysLog.setTitle(logAnn.value());
        sysLog.setParams(JSON.toJSONString(paramMap));
        // 发布事件
        applicationContext.publishEvent(new LogEvent(sysLog));
        log.info("Request Result: {}", sysLog);
        return result;
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
     * 记录请求参数
     *
     * @param point         ProceedingJoinPoint
     */
    public Map<String, Object> logIngArgs(ProceedingJoinPoint point) {
        MethodSignature ms = (MethodSignature) point.getSignature();
        Method method = ms.getMethod();
        Object[] args = point.getArgs();
        // 请求参数处理
        final Map<String, Object> paraMap = new HashMap<>(16);
        // 一次请求只能有一个 request body
        Object requestBodyValue = null;
        for (int i = 0; i < args.length; i++) {
            // 读取方法参数
            MethodParameter methodParam = getMethodParameter(method, i);
            // PathVariable 参数跳过
            PathVariable pathVariable = methodParam.getParameterAnnotation(PathVariable.class);
            if (pathVariable != null) {
                continue;
            }
            RequestBody requestBody = methodParam.getParameterAnnotation(RequestBody.class);
            String parameterName = methodParam.getParameterName();
            Object value = args[i];
            // 如果是body的json则是对象
            if (requestBody != null) {
                requestBodyValue = value;
                continue;
            }
            // 处理 参数
            if (value instanceof HttpServletRequest) {
                paraMap.putAll(((HttpServletRequest) value).getParameterMap());
                continue;
            } else if (value instanceof WebRequest) {
                paraMap.putAll(((WebRequest) value).getParameterMap());
                continue;
            } else if (value instanceof HttpServletResponse) {
                continue;
            } else if (value instanceof MultipartFile) {
                MultipartFile multipartFile = (MultipartFile) value;
                String name = multipartFile.getName();
                String fileName = multipartFile.getOriginalFilename();
                paraMap.put(name, fileName);
                continue;
            } else if (value instanceof List) {
                List<?> list = (List<?>) value;
                AtomicBoolean isSkip = new AtomicBoolean(false);
                for (Object o : list) {
                    if ("StandardMultipartFile".equalsIgnoreCase(o.getClass().getSimpleName())) {
                        isSkip.set(true);
                        break;
                    }
                }
                if (isSkip.get()) {
                    paraMap.put(parameterName, "此参数不能序列化为json");
                    continue;
                }
            }
            // 参数名
            RequestParam requestParam = methodParam.getParameterAnnotation(RequestParam.class);
            String paraName = parameterName;
            if (requestParam != null && StringUtils.isEmpty(requestParam.value())) {
                paraName = requestParam.value();
            }
            if (value == null) {
                paraMap.put(paraName, null);
            } else if (ClassUtil.isPrimitiveOrWrapper(value.getClass())) {
                paraMap.put(paraName, value);
            } else if (value instanceof InputStream) {
                paraMap.put(paraName, "InputStream");
            } else if (value instanceof InputStreamSource) {
                paraMap.put(paraName, "InputStreamSource");
            } else if (!StringUtils.isEmpty(JSON.toJSONString(value))) {
                // 判断模型能被 json 序列化，则添加
                paraMap.put(paraName, value);
            } else {
                paraMap.put(paraName, "此参数不能序列化为json");
            }
        }
       return paraMap;
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
