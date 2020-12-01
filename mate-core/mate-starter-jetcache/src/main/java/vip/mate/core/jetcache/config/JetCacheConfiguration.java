package vip.mate.core.jetcache.config;

import com.alicp.jetcache.anno.CacheConsts;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.alicp.jetcache.anno.support.GlobalCacheConfig;
import com.alicp.jetcache.anno.support.SpringConfigProvider;
import com.alicp.jetcache.embedded.EmbeddedCacheBuilder;
import com.alicp.jetcache.embedded.LinkedHashMapCacheBuilder;
import com.alicp.jetcache.redis.springdata.RedisSpringDataCacheBuilder;
import com.alicp.jetcache.support.FastjsonKeyConvertor;
import com.alicp.jetcache.support.JavaValueDecoder;
import com.alicp.jetcache.support.JavaValueEncoder;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import vip.mate.core.common.factory.YamlPropertySourceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * JetCache配置
 *
 * @author pangu
 */
@Configuration
@EnableMethodCache(basePackages = "vip.mate.system")
@EnableCreateCacheAnnotation
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-jetcache.yml")
public class JetCacheConfiguration {
	@Bean
	public RedisClient redisClient() {
		RedisClient client = RedisClient.create("redis://127.0.0.1");
		client.setOptions(ClientOptions.builder().
				disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
				.build());
		return client;
	}

	@Bean
	public SpringConfigProvider springConfigProvider() {
		return new SpringConfigProvider();
	}

	@Bean
	public GlobalCacheConfig config(SpringConfigProvider configProvider, RedisClient redisClient) {
		Map localBuilders = new HashMap();
		EmbeddedCacheBuilder localBuilder = LinkedHashMapCacheBuilder
				.createLinkedHashMapCacheBuilder()
				.keyConvertor(FastjsonKeyConvertor.INSTANCE);
		localBuilders.put(CacheConsts.DEFAULT_AREA, localBuilder);

		Map remoteBuilders = new HashMap(6);
		RedisSpringDataCacheBuilder<?> redisSpringDataCacheBuilder = RedisSpringDataCacheBuilder.createBuilder()
				.keyConvertor(FastjsonKeyConvertor.INSTANCE)
				.valueEncoder(JavaValueEncoder.INSTANCE)
				.valueDecoder(JavaValueDecoder.INSTANCE);
		remoteBuilders.put(CacheConsts.DEFAULT_AREA, redisSpringDataCacheBuilder);

		GlobalCacheConfig globalCacheConfig = new GlobalCacheConfig();
		// globalCacheConfig.setConfigProvider(configProvider);//for jetcache <=2.5
		globalCacheConfig.setLocalCacheBuilders(localBuilders);
		globalCacheConfig.setRemoteCacheBuilders(remoteBuilders);
		globalCacheConfig.setStatIntervalMinutes(15);
		globalCacheConfig.setAreaInCacheName(false);

		return globalCacheConfig;
	}

}
