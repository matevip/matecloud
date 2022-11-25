/*
 *
 *  Copyright 2020-2030, MateCloud-Plus,Beijing DaoTianDi Technology Inc. All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *   Neither the name of the mate.vip developer nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *   Author: pangu(7333791@qq.com)
 *
 *   此软件为商业软件，未经购买不得使用
 *   禁止将本产品的部分或全部代码和资源进行任何形式的开源（尤其上传GitHub、Gitee等开源平台）
 *   由此衍生的源代码的知识产权由购买方拥有，但是未经DaoTianDi官方许可，不得将修改后的源代码提供给任何第三方
 *
 */

package vip.mate.core.swagger.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.swagger.props.SwaggerProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Swagger配置
 *
 * @author matevip
 * @date 2022-11-4
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "springdoc.plus.enabled", havingValue = "true", matchIfMissing = true)
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-swagger.yml")
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

    @Value("${spring.mvc.servlet.path:/}")
    private String servletPath;
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public OpenAPI springDocOpenApi(SwaggerProperties swaggerProperties) {
        if (swaggerProperties == null) {
            return new OpenAPI();
        }
        //配置认证、请求头参数
        Components components = new Components();
        List<SecurityRequirement> security = new ArrayList<>();
        if (!CollectionUtils.isEmpty(swaggerProperties.getSecuritySchemes())) {
            for (Map.Entry<String, SecurityScheme> entry : swaggerProperties.getSecuritySchemes().entrySet()) {
                components.addSecuritySchemes(entry.getKey(), entry.getValue());
                SecurityRequirement requirement = new SecurityRequirement();
                requirement.addList(entry.getKey());
                security.add(requirement);
            }
        }

        Info info = new Info();
        SwaggerProperties.PlusInfo plusInfo = swaggerProperties.getInfo();
        if (plusInfo != null) {
            License license = new License();
            SwaggerProperties.PlusLicense plusLicense = plusInfo.getLicense();
            if (plusLicense != null) {
                license.name(plusLicense.getName()).url(plusLicense.getUrl());
            }
            Contact contact = new Contact();
            SwaggerProperties.PlusContact plusContact = plusInfo.getContact();
            if (plusContact != null) {
                contact.email(plusContact.getEmail()).name(plusContact.getName()).url(plusContact.getUrl());
            }
            info.title(plusInfo.getTitle()).description(plusInfo.getDescription())
                    .termsOfService(plusInfo.getTermsOfService()).version(plusInfo.getVersion() == null ? version() : plusInfo.getVersion())
                    .contact(contact).license(license)
            ;
        }
        // 接口调试路径
        List<Server> servers = swaggerProperties.getServers();
        if (CollectionUtils.isEmpty(servers)) {
            servers = new ArrayList<>();
        }
        servers.add(localServer());
        return new OpenAPI().components(components)
                .servers(servers)
                .info(info).externalDocs(swaggerProperties.getExternal())
                .security(security);
    }

    private Server localServer() {
        Server server = new Server();
        log.warn("=================================== 提示 =================================== ");
        log.warn("如果你要修改项目名称规则或网关路由配置或者网关端口,请调整此处取值逻辑");
        server.setUrl("http://127.0.0.1:10001/" + applicationName + servletPath);
        log.warn("=================================== 提示 =================================== ");
        server.setDescription("本地服务环境");
        return server;
    }

    private String version() {
        return "Spring Boot Version: " + SpringBootVersion.getVersion();
    }
}
