package vip.mate.core.database.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.AllArgsConstructor;
import org.apache.ibatis.type.EnumTypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.database.handler.MateMetaObjectHandler;
import vip.mate.core.database.props.TenantProperties;
import vip.mate.core.mybatis.injector.MateSqlInjector;
import vip.mate.core.mybatis.interceptor.SqlLogInterceptor;
import vip.mate.core.mybatis.props.MateMybatisProperties;

/**
 * mybatis plus配置中心
 *
 * @author xuzhanfu
 * @author L.cm
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@AllArgsConstructor
@EnableTransactionManagement
@EnableConfigurationProperties(MateMybatisProperties.class)
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
	 * sql 注入
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new MateSqlInjector();
	}

	/**
	 * sql 日志
	 */
	@Bean
	@Profile({"local", "dev", "test"})
	@ConditionalOnProperty(value = "mybatis-plus.sql-log.enable", matchIfMissing = true)
	public SqlLogInterceptor sqlLogInterceptor() {
		return new SqlLogInterceptor();
	}

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
		// BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();
		interceptor.addInnerInterceptor(paginationInnerInterceptor);
		// interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
		return interceptor;
	}

	/**
	 * 自动填充数据
	 */
	@Bean
	@ConditionalOnMissingBean(MateMetaObjectHandler.class)
	public MateMetaObjectHandler mateMetaObjectHandler() {
		return new MateMetaObjectHandler();
	}


	/**
	 * IEnum 枚举配置
	 */
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return configuration -> {
			configuration.setDefaultEnumTypeHandler(EnumTypeHandler.class);
			// 关闭 mybatis 默认的日志
			configuration.setLogPrefix("log.mybatis");
		};
	}

	/**
	 * mybatis-plus 乐观锁拦截器
	 */
	@Bean
	public OptimisticLockerInnerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInnerInterceptor();
	}
}
