package vip.mate.core.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vip.mate.core.common.constant.MateConstant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Swagger配置类，提供给WEB服务使用
 * @author pangu
 * 2020-7-5
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Profile({"!prod"})
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration implements WebMvcConfigurer {

    /**
     * 排除排springboot的错误和监控端点的路径
     */
    private static final List<String> DEFAULT_EXCLUDE_PATH = Arrays.asList("/error", "/actuator/**");
    private static final String BASE_PATH = "/**";

    @Bean(value = "userApi")
    @Order(value = 1)
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(groupApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("vip.mate"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping("/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo groupApiInfo(){
        return new ApiInfoBuilder()
                .title("MateCLoud文档管理中心")
                .description("MateCloud文档管理")
                .license("Powered by MateCloud")
                .termsOfServiceUrl("http://mate.vip/")
                .contact(new Contact("pangu", "http://doc.mate.vip", "7333791@qq.com"))
                .version(MateConstant.MATE_APP_VERSION)
                .build();
    }


    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
        apiKeyList.add(new ApiKey("Mate-Auth", "Mate-Auth", "header"));
        return apiKeyList;
    }

    /**
     * swagger2 认证的安全上下文
     */
    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
}
