package vip.mate.core.lock.config.strategy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;
import vip.mate.core.lock.constant.GlobalConstant;
import vip.mate.core.lock.props.RedissonProperties;

/**
 * 哨兵方式Redis连接配置
 *
 * @author pangu
 * @date 2020-10-22
 */
@Slf4j
public class SentinelRedissonConfigStrategyImpl implements RedissonConfigStrategy {

	@Override
	public Config createRedissonConfig(RedissonProperties redissonProperties) {
		Config config = new Config();
		try {
			String address = redissonProperties.getAddress();
			String password = redissonProperties.getPassword();
			int database = redissonProperties.getDatabase();
			String[] addrTokens = address.split(",");
			String sentinelAliasName = addrTokens[0];
			/**设置redis配置文件sentinel.conf配置的sentinel别名*/
			config.useSentinelServers()
					.setMasterName(sentinelAliasName);
			config.useSentinelServers().setDatabase(database);
			if (StringUtils.isNotBlank(password)) {
				config.useSentinelServers().setPassword(password);
			}
			/**设置sentinel节点的服务IP和端口*/
			for (int i = 1; i < addrTokens.length; i++) {
				config.useSentinelServers().addSentinelAddress(GlobalConstant.REDIS_CONNECTION_PREFIX.getConstant_value() + addrTokens[i]);
			}
			log.info("初始化[sentinel]方式Config,redisAddress:" + address);
		} catch (Exception e) {
			log.error("sentinel Redisson init error", e);
			e.printStackTrace();
		}
		return config;
	}
}
