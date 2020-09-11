package vip.mate.core.log.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.ProviderConstant;
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
     * 日志设置
     * @param commonLog　CommonLog对象
     * @return Result
     */
    @PostMapping(ProviderConstant.PROVIDER_LOG_SET)
    Result<Boolean> set(@RequestBody CommonLog commonLog);

}
