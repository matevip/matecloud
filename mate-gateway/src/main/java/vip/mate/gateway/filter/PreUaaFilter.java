package vip.mate.gateway.filter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import vip.mate.core.cloud.props.MateApiProperties;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.util.ResponseUtil;
import vip.mate.core.common.util.SecurityUtil;
import vip.mate.core.common.util.StringPool;
import vip.mate.core.common.util.TokenUtil;
import vip.mate.core.redis.core.RedisService;

/**
 * 网关统一的token验证
 *
 * @author pangu
 * @since 1.5.8
 */
@Slf4j
@Component
@AllArgsConstructor
public class PreUaaFilter implements GlobalFilter, Ordered {

	private final MateApiProperties mateApiProperties;

	private final RedisService redisService;

	/**
	 * 路径前缀以/mate开头，如mate-system
	 */
	public static final String PATH_PREFIX = "/mate";

	/**
	 * 索引自1开头检索，跳过第一个字符就是检索的字符的问题
	 */
	public static final int FROM_INDEX = 1;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 如果未启用网关验证，则跳过
		if (!mateApiProperties.getEnable()) {
			return chain.filter(exchange);
		}

		//　如果在忽略的url里，则跳过
		String path = replacePrefix(exchange.getRequest().getURI().getPath());
		String requestUrl = exchange.getRequest().getURI().getRawPath();
		if (ignore(path) || ignore(requestUrl)) {
			return chain.filter(exchange);
		}

		// 验证token是否有效
		ServerHttpResponse resp = exchange.getResponse();
		String headerToken = exchange.getRequest().getHeaders().getFirst(Oauth2Constant.HEADER_TOKEN);
		if (headerToken == null) {
			return unauthorized(resp, "没有携带Token信息！");
		}
		String token = TokenUtil.getToken(headerToken);
		Claims claims = SecurityUtil.getClaims(token);
		if (claims == null) {
			return unauthorized(resp, "token已过期或验证不正确！");
		}

		// 判断token是否存在于redis,对于只允许一台设备场景适用。
		// 如只允许一台设备登录，需要在登录成功后，查询key是否存在，如存在，则删除此key，提供思路。
		boolean hasKey = redisService.hasKey("auth:" + token);
		log.debug("查询token是否存在: " + hasKey);
		if (!hasKey) {
			return unauthorized(resp, "登录超时，请重新登录");
		}
		return chain.filter(exchange);
	}

	/**
	 * 检查是否忽略url
	 * @param path 路径
	 * @return boolean
	 */
	private boolean ignore(String path) {
		return mateApiProperties.getIgnoreUrl().stream()
				.map(url -> url.replace("/**", ""))
				.anyMatch(path::startsWith);
	}

	/**
	 * 移除模块前缀
	 * @param path 路径
	 * @return String
	 */
	private String replacePrefix(String path) {
		if (path.startsWith(PATH_PREFIX)) {
			return path.substring(path.indexOf(StringPool.SLASH, FROM_INDEX));
		}
		return path;
	}

	private Mono<Void> unauthorized(ServerHttpResponse resp, String msg) {
		return ResponseUtil.webFluxResponseWriter(resp, MateConstant.JSON_UTF8, HttpStatus.UNAUTHORIZED, msg); }

	@Override
	public int getOrder() {
		return MateConstant.MATE_UAA_FILTER_ORDER;
	}

	public static void main(String[] args) {

	}
}
