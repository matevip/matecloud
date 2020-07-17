package vip.mate.core.minio.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import vip.mate.core.minio.core.MinioTemplate;
import vip.mate.core.minio.props.MinioProperties;

@AllArgsConstructor
@EnableConfigurationProperties({MinioProperties.class})
public class MinioConfiguration {
    private final MinioProperties properties;

    @Bean
    @ConditionalOnMissingBean(MinioTemplate.class)
    @ConditionalOnProperty(name = "mate.minio.url")
    MinioTemplate template() {
        return new MinioTemplate(
                properties.getUrl(),
                properties.getAccessKey(),
                properties.getSecretKey()
        );
    }
}
