package vip.mate.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.mate.core.common.api.Result;
import vip.mate.core.feign.constant.FeignConstant;
import vip.mate.system.entity.SysLog;

@FeignClient(value = FeignConstant.MATE_CLOUD_SYSTEM)
public interface ISysLogProvider {

    @PostMapping("/log/save")
    Result<Boolean> saveLog(@RequestBody SysLog sysLog);
//    Result<Boolean> saveLog(@RequestBody SysLog sysLog, @RequestHeader(MateConstant.X_REQUEST_ID) String traceId);

}
