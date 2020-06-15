package vip.mate.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.core.common.api.Result;
import vip.mate.core.feign.constant.FeignConstant;
import vip.mate.system.entity.User;

@FeignClient(
    value = FeignConstant.MATE_CLOUD_SYSTEM
)
public interface IUserProvider {

    @GetMapping("/provider/user-info-by-id")
    Result<User> userInfoById(@RequestParam("userId") Long userId);

    @GetMapping("/provider/user-info")
    Result<User> loadUserByUserName(@RequestParam("userName") String userName);
}
