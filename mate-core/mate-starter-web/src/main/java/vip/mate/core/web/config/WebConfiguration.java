package vip.mate.core.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vip.mate.core.web.service.TokenService;
import vip.mate.core.web.service.impl.TokenServiceImpl;
import vip.mate.core.web.support.LoginUserArgumentResolver;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public TokenService tokenService(){
        return new TokenServiceImpl();
    };

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //注入用户信息
        argumentResolvers.add(new LoginUserArgumentResolver(tokenService()));
    }
}
