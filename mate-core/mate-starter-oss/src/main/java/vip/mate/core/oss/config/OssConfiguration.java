package vip.mate.core.oss.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import vip.mate.core.common.constant.ComponentConstant;
import vip.mate.core.oss.controller.OssEndpoint;
import vip.mate.core.oss.core.OssTemplate;
import vip.mate.core.oss.props.OssProperties;

/**
 * AWS自动配置类
 * @author pangu
 * @since 1.0.0
 */
@Slf4j
@Configuration
public class OssConfiguration {

    @Autowired
    @SuppressWarnings("all")
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @SuppressWarnings("all")
    private OssProperties properties;

    @Bean
    @ConditionalOnMissingBean(OssTemplate.class)
    @ConditionalOnProperty(name = "oss.enable", havingValue = "true", matchIfMissing = true)
    public OssTemplate ossTemplate() {
        return new OssTemplate(properties);
    }

    @Bean
    @ConditionalOnProperty(name = "oss.info", havingValue = "true")
    public OssEndpoint ossEndpoint(OssTemplate template) {
        return new OssEndpoint(template);
    }

    @Bean
    @RefreshScope
    public OssProperties ossProperties(){
        return (OssProperties) redisTemplate.opsForValue().get(ComponentConstant.OSS_DEFAULT);
    }
}
