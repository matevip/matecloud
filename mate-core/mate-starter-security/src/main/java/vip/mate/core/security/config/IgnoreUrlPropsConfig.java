package vip.mate.core.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
@Component
public class IgnoreUrlPropsConfig {

    private List<String> urls = new ArrayList<>();

    private List<String> client = new ArrayList<>();
}
