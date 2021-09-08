package vip.mate.core.lock.config.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import vip.mate.core.lock.constant.GlobalConstant;
import vip.mate.core.lock.props.RedissonProperties;

/**
 * 集群方式Redisson配置
 *
 * @author pangu
 * @date 2020-10-22
 */
@Slf4j
public class ClusterRedissonConfigStrategyImpl implements RedissonConfigStrategy {

	@Override
	public Config createRedissonConfig(RedissonProperties redissonProperties) {
		Config config = new Config();
		try {
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			String[] addrTokens = address.split(",");
			// 设置cluster节点的服务IP和端口
			for (String addrToken : addrTokens) {
				config.useClusterServers()
						.addNodeAddress(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstant_value() + addrToken);
				if (StringUtils.isNotBlank(password)) {
					config.useClusterServers().setPassword(password);
				}
			}
			log.info("初始化[cluster]方式Config,redisAddress:" + address);
		} catch (Exception e) {
			log.error("cluster Redisson init error", e);
			e.printStackTrace();
		}
		return config;
	}
}
