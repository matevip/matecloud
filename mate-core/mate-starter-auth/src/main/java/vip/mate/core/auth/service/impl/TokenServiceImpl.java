package vip.mate.core.auth.service.impl;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.mate.core.auth.service.TokenService;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.TokenException;
import vip.mate.core.common.util.HttpContextUtil;
import vip.mate.core.common.util.TokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String checkToken(HttpServletRequest request) throws TokenException {
        String headerToken = request.getHeader(Oauth2Constant.HEADER_TOKEN);
        if (StringUtils.isBlank(headerToken)) {
            throw new TokenException("没有携带Token信息！");
        }
        String token = StringUtils.isNotBlank(headerToken) ? TokenUtil.getToken(headerToken) : "";
        Claims claims = null;
        if (StringUtils.isNotBlank(token)) {
            try {
                claims = TokenUtil.getClaims(token);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                throw new TokenException("Token已过期！");
            }
        }
        return (String) claims.get("userId");
    }
}
