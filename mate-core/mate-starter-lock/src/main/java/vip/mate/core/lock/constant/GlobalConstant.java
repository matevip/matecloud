package vip.mate.core.lock.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局常量枚举
 *
 * @author pangu
 */
@Getter
@AllArgsConstructor
public enum GlobalConstant {

	/**
	 * Redis地址连接前缀
	 */
	REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

	private final String constant_value;
	private final String constant_desc;
}
