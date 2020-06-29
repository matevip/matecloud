package vip.mate.core.auth.service;


import vip.mate.core.common.exception.TokenException;
import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /**
     * 检验token
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request) throws TokenException;
}
