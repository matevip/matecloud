package vip.mate.core.log.event;

import org.springframework.context.ApplicationEvent;
import vip.mate.core.log.entity.SysLog;

/**
 * 日志事件
 * @author pangu 7333791@qq.com
 * @since 2020-7-15
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(SysLog source) {
        super(source);
    }
}
