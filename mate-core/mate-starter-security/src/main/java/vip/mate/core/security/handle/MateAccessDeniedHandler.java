package vip.mate.core.security.handle;

import cn.hutool.core.util.CharsetUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MateAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		ResponseUtil.responseWriter(httpServletResponse, CharsetUtil.UTF_8, HttpServletResponse.SC_FORBIDDEN, Result.fail("没有访问权限"));
	}
}
