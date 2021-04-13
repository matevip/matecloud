package vip.mate.core.ide.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 幂等枚举类
 *
 * @author pangu
 */
@Getter
@AllArgsConstructor
public enum IdeTypeEnum {

	/**
	 * 0+1
	 */
	ALL(0, "ALL"),
	/**
	 * ruid 是针对每一次请求的
	 */
	RID(1, "RID"),
	/**
	 * key+val 是针对相同参数请求
	 */
	KEY(2, "KEY");

	private final Integer index;
	private final String title;
}
