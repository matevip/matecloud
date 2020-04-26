package vip.mate.admin.feign;

import lombok.extern.slf4j.Slf4j;
import vip.mate.admin.entity.User;
import vip.mate.common.annotation.Fallback;
import vip.mate.common.api.ApiResult;

@Slf4j
@Fallback
public class UserApiFallback implements IUserApi {

    @Override
    public ApiResult<User> userInfoById(Long userId) {
        log.error("调用 userInfoById 失败");
        return ApiResult.fail("调用 userInfoById 失败");
    }

    @Override
    public ApiResult<User> loadUserByUserName(String userName) {
        log.error("调用 loadUserByUserName 失败");
        return ApiResult.fail("调用 loadUserByUserName 失败");
    }
}
