package vip.mate.system.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.entity.SysLog;
import vip.mate.core.log.feign.ISysLogProvider;
import vip.mate.system.service.ISysLogService;

/**
 * 日志feign或者Dubbo调用
 * @author pangu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Feign或者Dubbo调用日志操作")
public class SysLogProvider implements ISysLogProvider {

    private final ISysLogService sysLogService;

    @Override
    @PostMapping("/provider/log/save")
    @ApiOperation(value = "保存日志", notes = "保存日志")
    public Result<Boolean> saveLog(SysLog sysLog) {
        return Result.data(sysLogService.save(sysLog));
    }
}
