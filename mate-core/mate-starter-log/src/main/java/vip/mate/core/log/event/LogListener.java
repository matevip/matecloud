package vip.mate.core.log.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vip.mate.core.log.entity.SysLog;
import vip.mate.core.log.feign.ISysLogProvider;

/**
 * 注解形式，异步监听事件
 * @author pangu 7333791@qq.com
 * @since 2020-7-15
 */
@Slf4j
@Component
@AllArgsConstructor
public class LogListener {

    private final ISysLogProvider sysLogProvider;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
        sysLogProvider.saveLog(sysLog);
    }

}
