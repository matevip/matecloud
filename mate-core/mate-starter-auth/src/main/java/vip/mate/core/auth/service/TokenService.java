package vip.mate.core.auth.service;


import io.jsonwebtoken.Claims;
import vip.mate.core.common.exception.TokenException;
import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /**
     * 检验token
     * @return
     */
    Claims checkToken(HttpServletRequest request) throws TokenException;
}
