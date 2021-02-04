package vip.mate.core.database.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分类类型
 *
 * @author pangu
 */
@Deprecated
@Getter
@AllArgsConstructor
public enum PageTypeEnum {
	/**
	 * 默认当前页
	 */
	CURRENT(1),

	/**
	 * 默认每页数据
	 */
	SIZE(20);

	private final Integer number;
}
