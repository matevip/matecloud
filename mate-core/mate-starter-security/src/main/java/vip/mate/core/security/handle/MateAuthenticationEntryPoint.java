package vip.mate.core.security.handle;

import cn.hutool.core.util.CharsetUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MateAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
		int status = HttpServletResponse.SC_UNAUTHORIZED;
		ResponseUtil.responseWriter(response, CharsetUtil.UTF_8, status, Result.fail(status, "访问令牌不合法"));
	}
}
