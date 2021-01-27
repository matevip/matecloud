package vip.mate.core.web.config;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.PathProvider;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.DefaultPathProvider;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.web.props.MateSwaggerProperties;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Swagger配置类，提供给WEB服务使用
 *
 * @author pangu
 * 2020-7-5
 */
@Configuration
@EnableSwagger2WebMvc
@AllArgsConstructor
//@Profile({"!prod"})
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties(MateSwaggerProperties.class)
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-swagger.yml")
public class SwaggerConfiguration implements WebMvcConfigurer {

	private final MateSwaggerProperties swaggerProperties;

	@Bean
	public PathProvider pathProvider() {
		return new DefaultPathProvider() {
			@Override
			public String getOperationPath(String operationPath) {
				return super.getOperationPath(operationPath);
			}
		};
	}

	/**
	 * Swagger忽略的参数类型
	 */
	private final Class[] ignoredParameterTypes = new Class[]{
			ServletRequest.class,
			ServletResponse.class,
			HttpServletRequest.class,
			HttpServletResponse.class,
			HttpSession.class,
			ApiIgnore.class
	};

	@Bean(value = "userApi")
	public Docket createRestApi() {
		ApiSelectorBuilder apiSelectorBuilder = new Docket(DocumentationType.SWAGGER_2)
				// 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
				.apiInfo(groupApiInfo())
				// 设置哪些接口暴露给Swagger展示
				.select();
		if (swaggerProperties.getBasePackage() == null) {
			// 扫描所有有注解的api，用这种方式更灵活
			apiSelectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
		} else {
			// 扫描指定的包
			apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()));
		}
		return apiSelectorBuilder.paths(PathSelectors.any())
				.build()
				.enable(swaggerProperties.isEnable())
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts())
				.pathProvider(pathProvider())
				.ignoredParameterTypes(ignoredParameterTypes)
				.pathMapping("/");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
	}

	private ApiInfo groupApiInfo() {
		return new ApiInfoBuilder()
				.title(swaggerProperties.getTitle())
				.description(swaggerProperties.getDescription())
				.license(swaggerProperties.getLicense())
				.termsOfServiceUrl(swaggerProperties.getServiceUrl())
				.contact(new Contact(swaggerProperties.getContactName(),
						swaggerProperties.getContactUrl(),
						swaggerProperties.getContactEmail()))
				.version(MateConstant.MATE_APP_VERSION)
				.build();
	}


	private List<ApiKey> securitySchemes() {
		List<ApiKey> apiKeyList = new ArrayList<>();
		apiKeyList.add(new ApiKey("Authorization", "Authorization", "header"));
		apiKeyList.add(new ApiKey("Mate-Auth", "Mate-Auth", "header"));
		return apiKeyList;
	}

	/**
	 * swagger2 认证的安全上下文
	 */
	private List<SecurityContext> securityContexts() {
		List<SecurityContext> securityContexts = new ArrayList<>();
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
		List<SecurityReference> securityReferences = new ArrayList<>();
		securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
		return securityReferences;
	}
}
