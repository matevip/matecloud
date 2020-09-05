package vip.mate.core.log.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.log.feign.ICommonLogProvider;
import vip.mate.core.log.feign.ISysLogProvider;

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
    private boolean enable = false;

    public LogListener(ISysLogProvider sysLogProvider, boolean enable) {
        this.sysLogProvider = sysLogProvider;
        this.enable = enable;
    }

    public LogListener(ICommonLogProvider commonLogProvider, boolean enable) {
        this.commonLogProvider = commonLogProvider;
        this.enable = enable;
    }
    @Async
    @Order
    @EventListener(LogEvent.class)
    public void saveSysLog(LogEvent event) {
        CommonLog commonLog = (CommonLog) event.getSource();
        // 发送日志到kafka
        log.debug("发送日志:{}", commonLog);
        if (this.enable) {
            commonLogProvider.sendCommonLog(commonLog);
        } else {
            sysLogProvider.saveLog(commonLog);
        }
    }

}
