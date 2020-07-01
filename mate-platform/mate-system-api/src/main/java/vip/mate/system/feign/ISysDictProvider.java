package vip.mate.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.core.common.api.Result;
import vip.mate.core.feign.constant.FeignConstant;
import vip.mate.system.entity.SysDict;

import java.util.List;

@FeignClient(value = FeignConstant.MATE_CLOUD_SYSTEM)
public interface ISysDictProvider {

    @GetMapping("/provider/sys-dict/get-value")
    Result<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") String dictKey);

    @GetMapping("/provider/sys-dict/get-list")
    Result<List<SysDict>> getList(@RequestParam("code") String code);
}
