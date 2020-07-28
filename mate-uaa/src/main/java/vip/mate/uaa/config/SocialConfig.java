package vip.mate.uaa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * social的配置参数
 * @author xuzf
 * @since 2020-07-26
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "social.vue")
public class SocialConfig {

    private String url;
}
