package vip.mate.sso.controller;

import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.util.SecurityUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class IndexController {

	private final HttpServletRequest request;

	/**
	 * 欢迎页
	 *
	 * @return
	 */
	@GetMapping("/")
	public Result<?> index(ModelMap modelMap) {
		LoginUser user = SecurityUtil.getUsername(request);
		modelMap.put("user", user);
		return Result.data(modelMap);
	}
}
