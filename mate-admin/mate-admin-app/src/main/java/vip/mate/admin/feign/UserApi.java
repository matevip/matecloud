package vip.mate.admin.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.admin.entity.User;
import vip.mate.admin.service.IUserService;
import vip.mate.common.api.ApiResult;


@RestController
@AllArgsConstructor
public class UserApi implements IUserApi {

    private final IUserService userService;

    @Override
    @GetMapping("/api/user-info-by-id")
    public ApiResult<User> userInfoById(Long userId) {
        User user = userService.getById(userId);
        return ApiResult.data(user);
    }

    @Override
    @GetMapping("/api/user-info")
    public ApiResult<User> loadUserByUserName(String userName) {
        User user = new User();
        user.setAccount(userName);
        return ApiResult.data(userService.getOne(new QueryWrapper<>(user)));
    }
}
