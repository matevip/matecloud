package vip.mate.core.gray.context;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import vip.mate.core.common.constant.MateConstant;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 版本上下文过滤器
 *
 * @author madi
 * @date 2020-12-28 13:41
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VersionContextHolderFilter extends GenericFilterBean {

	@Override
	@SneakyThrows
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String version = request.getHeader(MateConstant.VERSION);
		log.debug("获取header中的VERSION为:{}", version);
		VersionContextHolder.setVersion(version);
		filterChain.doFilter(request, response);
		VersionContextHolder.clear();
	}
}
