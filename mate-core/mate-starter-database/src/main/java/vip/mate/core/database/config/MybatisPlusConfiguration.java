package vip.mate.core.database.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.database.handler.MateMetaObjectHandler;
import vip.mate.core.database.props.TenantProperties;

/**
 * mybatis plus配置中心
 * @author xuzhanfu
 */
@Slf4j
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-db.yml")
@MapperScan("vip.mate.**.mapper.**")
public class MybatisPlusConfiguration {

    private final TenantProperties tenantProperties;

    private final TenantLineInnerInterceptor tenantLineInnerInterceptor;

    /**
     * 单页分页条数限制(默认无限制,参见 插件#handlerLimit 方法)
     */
    private static final Long MAX_LIMIT = 1000L;

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        boolean enableTenant = tenantProperties.getEnable();
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        if (enableTenant) {
            interceptor.addInnerInterceptor(tenantLineInnerInterceptor);
        }
        //分页插件: PaginationInnerInterceptor
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setMaxLimit(MAX_LIMIT);
        //防止全表更新与删除插件: BlockAttackInnerInterceptor
        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(Boolean.FALSE);
    }

    /**
     * 自动填充数据
     */
    @Bean
    @ConditionalOnMissingBean(MateMetaObjectHandler.class)
    public MateMetaObjectHandler mateMetaObjectHandler(){
        MateMetaObjectHandler metaObjectHandler = new MateMetaObjectHandler();
        log.info("MateMetaObjectHandler [{}]", metaObjectHandler);
        return metaObjectHandler;
    }
}
