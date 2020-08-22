package vip.mate.core.rule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.rule.service.IRuleCacheService;
import vip.mate.core.rule.service.impl.RuleCacheServiceImpl;

/**
 * 规则配置
 * @author pangu
 */
@Configuration
public class RuleConfiguration {

    @Bean
    public IRuleCacheService ruleCacheService() {
        return new RuleCacheServiceImpl();
    }
}
