package vip.mate.system.feign;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.system.entity.SysLog;
import vip.mate.system.service.ISysLogService;

/**
 * 日志feign或者Dubbo调用
 * @author pangu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Feign或者Dubbo调用日志操作")
public class SysLogProvider implements ISysLogProvider{

    private final ISysLogService sysLogService;

    @Override
    public Result<Boolean> saveLog(SysLog sysLog) {
        if (!ObjectUtils.isEmpty(sysLog)) {
            sysLogService.save(sysLog);
        }
        return Result.data(true);
    }
}
