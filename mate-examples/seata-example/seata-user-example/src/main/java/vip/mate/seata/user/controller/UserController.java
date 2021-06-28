package vip.mate.seata.user.controller;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.seata.user.entity.User;
import vip.mate.seata.user.feign.OrderProvider;
import vip.mate.seata.user.feign.PointProvider;
import vip.mate.seata.user.service.IUserService;

/**
 * 用户控制器
 *
 * @author pangu
 */
@RestController
@RequiredArgsConstructor
public class UserController {
	private final IUserService userService;
	private final OrderProvider orderProvider;
	private final PointProvider pointProvider;

	@GlobalTransactional(rollbackFor = Exception.class)
	@PostMapping("/user")
	public Result<String> createUser(@RequestBody User user) {
		userService.saveOrUpdate(user);
		pointProvider.createPoint();
		orderProvider.createOrder();
		return Result.condition(Boolean.TRUE);
	}
}
