package vip.mate.uaa.controller;

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
 * @author pangu
 * @link https://segmentfault.com/a/1190000020317220?utm_source=tag-newest
 */
@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
public class OauthController {

    private final TokenEndpoint tokenEndpoint;

    @Log(value = "用户登录", exception = "用户登录请求异常")
    @GetMapping("/token")
    public Result<?> getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @Log(value = "用户登录", exception = "用户登录请求异常")
    @PostMapping("/token")
    public Result<?> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    //自定义返回格式
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
