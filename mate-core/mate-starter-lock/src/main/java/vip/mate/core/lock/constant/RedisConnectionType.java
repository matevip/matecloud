package vip.mate.core.lock.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis连接方式
 * <p>
 * 包含:standalone-单节点部署方式
 * sentinel-哨兵部署方式
 * cluster-集群方式
 * masterslave-主从部署方式
 * </p>
 *
 * @author xuzhanfu
 */
@Getter
@AllArgsConstructor
public enum RedisConnectionType {
	/**
	 * 单节点部署方式
	 */
	STANDALONE("standalone", "单节点部署方式"),
	/**
	 * 哨兵部署方式
	 */
	SENTINEL("sentinel", "哨兵部署方式"),
	/**
	 * 集群部署方式
	 */
	CLUSTER("cluster", "集群方式"),
	/**
	 * 主从部署方式
	 */
	MASTERSLAVE("masterslave", "主从部署方式");

	private final String connection_type;
	private final String connection_desc;
}
