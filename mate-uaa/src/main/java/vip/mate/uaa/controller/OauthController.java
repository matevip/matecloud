package vip.mate.uaa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义Oauth2自定义返回格式
 *
 * @author pangu
 * @link https://segmentfault.com/a/1190000020317220?utm_source=tag-newest
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Tag(name = "Oauth2管理")
public class OauthController {

	private final TokenEndpoint tokenEndpoint;

	@Log(value = "用户登录", exception = "用户登录请求异常")
	@GetMapping("/token")
	@Operation(summary = "用户登录Get", description = "用户登录Get")
	public Result<?> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
		return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
	}

	@Log(value = "用户登录", exception = "用户登录请求异常")
	@PostMapping("/token")
	@Operation(summary = "用户登录Post", description = "用户登录Post")
	@Parameters({
			@Parameter(name = "grant_type", required = true,  description = "授权类型", in = ParameterIn.QUERY),
			@Parameter(name = "username", required = true,  description = "用户名", in = ParameterIn.QUERY),
			@Parameter(name = "password", required = true,  description = "密码", in = ParameterIn.QUERY),
			@Parameter(name = "scope", required = true,  description = "使用范围", in = ParameterIn.QUERY),
	})
	public Result<?> postAccessToken(Principal principal, @RequestBody Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
		return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
	}

	/**
	 * 自定义返回格式
	 *
	 * @param accessToken 　Token
	 * @return Result
	 */
	private Result<?> custom(OAuth2AccessToken accessToken) {
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
		data.put("accessToken", token.getValue());
		if (token.getRefreshToken() != null) {
			data.put("refreshToken", token.getRefreshToken().getValue());
		}
		return Result.data(data);
	}
}
