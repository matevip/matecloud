package vip.mate.core.security.util;

import vip.mate.core.common.constant.Oauth2Constant;

import javax.servlet.http.HttpServletRequest;

public class SecurityUtil {

    public static String getToken(HttpServletRequest request) {
        String headerToken = request.getHeader(Oauth2Constant.HEADER_TOKEN);
        return TokenUtil.getToken(headerToken);
    }
}
