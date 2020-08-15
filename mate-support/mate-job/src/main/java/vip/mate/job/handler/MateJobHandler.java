package vip.mate.job.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时任务执行类
 */
@Slf4j
@Component
public class MateJobHandler {

    @XxlJob(value = "demoJobHandler")
    public ReturnT<?> demoJobHandler (){
        log.error("测试定时任务");
        return ReturnT.SUCCESS;
    }

}
