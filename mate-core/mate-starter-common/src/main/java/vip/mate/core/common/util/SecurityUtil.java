package vip.mate.core.common.util;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.context.UserContext;
import vip.mate.core.common.entity.LoginUser;
import vip.mate.core.common.exception.TokenException;

import javax.servlet.http.HttpServletRequest;

/**
 * token安全检测工具类
 *
 * @author pangu
 */
@Slf4j
public class SecurityUtil {


	/**
	 * 从HttpServletRequest里获取token
	 *
	 * @param request HttpServletRequest
	 * @return token
	 */
	public static String getHeaderToken(HttpServletRequest request) {
		return request.getHeader(Oauth2Constant.HEADER_TOKEN);
	}

	/**
	 * 从HttpServletRequest里获取token
	 *
	 * @param request HttpServletRequest
	 * @return token
	 */
	public static String getToken(HttpServletRequest request) {
		String headerToken = getHeaderToken(request);
		if (StringUtil.isBlank(headerToken)) {
			throw new TokenException("没有携带Token信息！");
		}
		return StringUtil.isNotBlank(headerToken) ? TokenUtil.getToken(headerToken) : "";
	}

	/**
	 * 从Token解析获取Claims对象
	 *
	 * @param token Mate-Auth获取的token
	 * @return Claims
	 */
	public static Claims getClaims(String token) {
		Claims claims = null;
		if (StringUtil.isNotBlank(token)) {
			try {
				claims = TokenUtil.getClaims(token);
			} catch (Exception e) {
				throw new TokenException("Token已过期！");
			}
		}
		return claims;
	}

	/**
	 * 从HttpServletRequest获取LoginUser信息
	 *
	 * @param request HttpServletRequest
	 * @return LoginUser
	 */
	public static LoginUser getUsername(HttpServletRequest request) {

		String token = getToken(request);
		Claims claims = getClaims(token);
		// 然后根据token获取用户登录信息，这里省略获取用户信息的过程
		LoginUser loginUser = new LoginUser();
		loginUser.setUserId(String.valueOf(claims.get(Oauth2Constant.MATE_USER_ID)));
		loginUser.setAccount((String) claims.get(Oauth2Constant.MATE_USER_NAME));
		loginUser.setRoleId(String.valueOf(claims.get(Oauth2Constant.MATE_ROLE_ID)));
		loginUser.setType(NumberUtil.stoi(String.valueOf(claims.get(Oauth2Constant.MATE_TYPE))));
		UserContext.setUser(loginUser);
		return loginUser;
	}
}
