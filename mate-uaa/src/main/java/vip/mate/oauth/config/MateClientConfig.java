package vip.mate.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vip.mate.oauth.service.ClientDetailsServiceImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MateClientConfig {

    @Resource
    private DataSource dataSource;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ClientDetailsServiceImpl clientDetailService() {
        ClientDetailsServiceImpl clientDetailsService = new ClientDetailsServiceImpl(dataSource);
        clientDetailsService.setRedisTemplate(redisTemplate);
        return clientDetailsService;
    }
}
