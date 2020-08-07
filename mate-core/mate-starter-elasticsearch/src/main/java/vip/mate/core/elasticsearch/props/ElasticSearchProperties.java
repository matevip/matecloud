package vip.mate.core.elasticsearch.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "mate.es")
public class ElasticSearchProperties {

    private List<String> uris = new ArrayList(Collections.singletonList("http://localhost:9200"));
    private String username;
    private String password;
    private Duration connectionTimeout = Duration.ofSeconds(1L);
    private Duration readTimeout = Duration.ofSeconds(30L);
}
