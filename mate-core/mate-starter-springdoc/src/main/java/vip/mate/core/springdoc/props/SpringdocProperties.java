package vip.mate.core.springdoc.props;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Springdoc配置类
 *
 * @author matevip
 * @since 2022-3-14
 */
@Data
@ConfigurationProperties(prefix = "springdoc.plus")
public class SpringdocProperties {

    private Boolean enabled = true;

    /**
     * 为什么不用 swagger v3 自带的呢？因为idea不提示 防止有人不会写
     */
    private PlusInfo info;
    private ExternalDocumentation external;
    /**
     * 主要适配 oauth2（http basic配置起来更容易,可以参考oauth2 配置）
     */
    private Map<String, SecurityScheme> securitySchemes;
    /**
     * serverUrl 默认 本地地址
     **/
    private List<Server> servers;


    @Data
    public static class PlusInfo {
        private String title = null;
        private String description = null;
        private String termsOfService = null;
        private PlusContact contact = null;
        private PlusLicense license = null;
        private String version = null;
    }

    @Data
    public static class PlusContact {
        private String name = null;
        private String url = null;
        private String email = null;
    }

    @Data
    public static class PlusLicense {
        private String name = null;
        private String url = null;
    }
}
