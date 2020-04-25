package vip.mate.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.admin.entity.User;
import vip.mate.common.api.ApiResult;

@FeignClient(
    value = "mate-admin"
)
public interface IUserApi {

    @GetMapping("/api/user-info-by-id")
    ApiResult<User> userInfoById(@RequestParam("userId") Long userId);

    @GetMapping("/api/user-info")
    ApiResult<User> loadUserByUserName(@RequestParam("userName") String userName);
}
