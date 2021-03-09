package vip.mate.core.log.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.log.feign.ICommonLogProvider;
import vip.mate.core.log.feign.ISysLogProvider;
import vip.mate.core.log.props.LogProperties;
import vip.mate.core.log.props.LogType;

/**
 * 注解形式，异步监听事件
 * @author pangu 7333791@qq.com
 * @since 2020-7-15
 */
@Slf4j
@Component
public class LogListener {

    private ISysLogProvider sysLogProvider;
    private ICommonLogProvider commonLogProvider;
    private LogProperties logProperties;
    public LogListener(){

    }
    public LogListener(ISysLogProvider sysLogProvider, LogProperties logProperties) {
        this.sysLogProvider = sysLogProvider;
        this.logProperties = logProperties;
    }

    public LogListener(ICommonLogProvider commonLogProvider, LogProperties logProperties) {
        this.commonLogProvider = commonLogProvider;
        this.logProperties = logProperties;
    }

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        CommonLog commonLog = (CommonLog) event.getSource();
        // 发送日志到kafka
        log.info("发送日志:{}", commonLog);
        if (logProperties.getLogType().equals(LogType.KAFKA)) {
            commonLogProvider.sendCommonLog(commonLog);
        } else {
            sysLogProvider.set(commonLog);
        }
    }

}
