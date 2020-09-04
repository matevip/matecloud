package vip.mate.core.log.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.feign.constant.FeignConstant;

/**
 * feign调用mate-system存储日志
 * @author pangu
 * @date 2020-7-1
 */
@FeignClient(value = FeignConstant.MATE_CLOUD_SYSTEM)
public interface ISysLogProvider {

    /**
     * 存储日志
     * @param commonLog
     * @return
     */
    @PostMapping("/provider/log/save")
    Result<Boolean> saveLog(@RequestBody CommonLog commonLog);
//    Result<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(MateConstant.X_REQUEST_ID) String traceId);

}
