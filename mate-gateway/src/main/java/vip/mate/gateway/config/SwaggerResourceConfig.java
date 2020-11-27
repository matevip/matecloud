package vip.mate.gateway.config;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * swagger配置
 *
 * @author pangu
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    /**
     * swagger2默认的url后缀
     */
    private static final String SWAGGER3URL = "/v3/api-docs";

    /**
     * 网关路由
     */
    private final RouteLocator routeLocator;


    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String self;

    @Autowired
    public SwaggerResourceConfig(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    private final static String EXCLUDE_MODULE = "mate-admin";

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        // 由于我的网关采用的是负载均衡的方式，因此我需要拿到所有应用的serviceId
        // 获取所有可用的host：serviceId
        // 当前排除掉mate-admin模块的doc
        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                .filter(route -> !self.equals(route.getUri().getHost()))
                .filter(route -> !route.getUri().getHost().equalsIgnoreCase(EXCLUDE_MODULE))
                .subscribe(route -> routeHosts.add(route.getUri().getHost()));

        // 记录已经添加过的server，存在同一个应用注册了多个服务在nacos上
        Set<String> repeated = new HashSet<>();
        routeHosts.forEach(instance -> {
            // 拼接url，样式为/serviceId/v3/api-info，当网关调用这个接口时，会自动通过负载均衡寻找对应的主机
            String url = "/" + instance + SWAGGER3URL;
            if (!repeated.contains(url)) {
                repeated.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(instance);
                swaggerResource.setSwaggerVersion("3.0.0");
                resources.add(swaggerResource);
            }
        });
        return resources;
    }
}
