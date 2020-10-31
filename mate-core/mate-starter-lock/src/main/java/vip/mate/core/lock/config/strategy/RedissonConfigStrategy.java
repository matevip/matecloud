package vip.mate.core.lock.config.strategy;

import org.redisson.config.Config;
import vip.mate.core.lock.props.RedissonProperties;

/**
 * Redisson配置构建接口
 *
 * @author pangu
 * @date 2020-10-22
 */
public interface RedissonConfigStrategy {

	/**
	 * 根据不同的Redis配置策略创建对应的Config
	 *
	 * @param redissonProperties redisson配置
	 * @return Config
	 */
	Config createRedissonConfig(RedissonProperties redissonProperties);
}
