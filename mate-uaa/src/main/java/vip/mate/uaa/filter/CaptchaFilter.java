package vip.mate.uaa.filter;

import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.CaptchaException;
import vip.mate.core.common.util.ResponseUtil;
import vip.mate.uaa.service.CaptchaService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class CaptchaFilter extends OncePerRequestFilter {

    private final CaptchaService captchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(Oauth2Constant.OAUTH_TOKEN, HttpMethod.POST.toString());

        if (matcher.matches(httpServletRequest) && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter(Oauth2Constant.GRANT_TYPE), Oauth2Constant.PASSWORD)){

            String key = httpServletRequest.getHeader(Oauth2Constant.CAPTCHA_HEADER_KEY);
            String code = httpServletRequest.getHeader(Oauth2Constant.CAPTCHA_HEADER_CODE);

            try {
                captchaService.check(key, code);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (CaptchaException captchaException){
                ResponseUtil.responseWriter(httpServletResponse, MediaType.APPLICATION_JSON_VALUE,
                        HttpServletResponse.SC_UNAUTHORIZED, Result.fail(HttpServletResponse.SC_UNAUTHORIZED,
                                captchaException.getMessage()));
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
