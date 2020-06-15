package vip.mate.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.core.common.api.ApiResult;
import vip.mate.system.entity.User;

@FeignClient(
    value = "mate-system",
    fallback = UserProviderFallback.class
)
public interface IUserProvider {

    @GetMapping("/provider/user-info-by-id")
    ApiResult<User> userInfoById(@RequestParam("userId") Long userId);

    @GetMapping("/provider/user-info")
    ApiResult<User> loadUserByUserName(@RequestParam("userName") String userName);
}
