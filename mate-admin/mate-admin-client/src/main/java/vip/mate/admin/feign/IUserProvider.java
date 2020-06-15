package vip.mate.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.admin.entity.User;
import vip.mate.core.common.api.ApiResult;

@FeignClient(
    value = "mate-admin",
    fallback = UserProviderFallback.class
)
public interface IUserProvider {

    @GetMapping("/provider/user-info-by-id")
    ApiResult<User> userInfoById(@RequestParam("userId") Long userId);

    @GetMapping("/provider/user-info")
    ApiResult<User> loadUserByUserName(@RequestParam("userName") String userName);
}
