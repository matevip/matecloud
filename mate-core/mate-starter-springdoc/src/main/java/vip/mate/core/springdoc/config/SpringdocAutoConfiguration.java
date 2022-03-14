package vip.mate.core.springdoc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import vip.mate.core.springdoc.props.SpringdocProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Springdoc自动配置
 *
 * @author matevip
 * @since 2022-3-14
 */
@Configuration
@ConditionalOnProperty(name = "springdoc.plus.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SpringdocProperties.class)
public class SpringdocAutoConfiguration {
    @Value("${server.port:8080}")
    private int port;
    @Value("${spring.mvc.servlet.path:/}")
    private String servletPath;


    @Bean
    @ConditionalOnMissingBean
    public SpringdocProperties springdocProperties() {
        return new SpringdocProperties();
    }

    @Bean
    public OpenAPI springDocOpenApi(SpringdocProperties springdocProperties) {
        if (springdocProperties == null) {
            return new OpenAPI();
        }
        //配置认证、请求头参数
        Components components = new Components();
        List<SecurityRequirement> security = new ArrayList<>();
        if (!CollectionUtils.isEmpty(springdocProperties.getSecuritySchemes())) {
            for (Map.Entry<String, SecurityScheme> entry : springdocProperties.getSecuritySchemes().entrySet()) {
                components.addSecuritySchemes(entry.getKey(), entry.getValue());
                SecurityRequirement requirement = new SecurityRequirement();
                requirement.addList(entry.getKey());
                security.add(requirement);
            }
        }

        Info info = new Info();
        SpringdocProperties.PlusInfo plusInfo = springdocProperties.getInfo();
        if (plusInfo != null) {
            License license = new License();
            SpringdocProperties.PlusLicense plusLicense = plusInfo.getLicense();
            if (plusLicense != null) {
                license.name(plusLicense.getName()).url(plusLicense.getUrl());
            }
            Contact contact = new Contact();
            SpringdocProperties.PlusContact plusContact = plusInfo.getContact();
            if (plusContact != null) {
                contact.email(plusContact.getEmail()).name(plusContact.getName()).url(plusContact.getUrl());
            }
            info.title(plusInfo.getTitle()).description(plusInfo.getDescription())
                    .termsOfService(plusInfo.getTermsOfService()).version(plusInfo.getVersion() == null ? version() : plusInfo.getVersion())
                    .contact(contact).license(license)
            ;
        }
        // 接口调试路径
        List<Server> servers = springdocProperties.getServers();
        if (CollectionUtils.isEmpty(servers)) {
            servers = new ArrayList<>();
        }
        servers.add(localServer());
        return new OpenAPI().components(components)
                .servers(servers)
                .info(info).externalDocs(springdocProperties.getExternal())
                .security(security);
    }

    private Server localServer() {
        Server server = new Server();
        server.setUrl("http://localhost:" + port + "" + servletPath);
        server.setDescription("本地服务环境");
        return server;
    }

    private String version() {
        return "Spring Boot Version: " + SpringBootVersion.getVersion();
    }

}
