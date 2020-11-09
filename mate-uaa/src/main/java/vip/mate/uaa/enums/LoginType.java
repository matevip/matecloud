package vip.mate.uaa.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录类型枚举类
 *
 * @author pangu
 */
@Getter
@AllArgsConstructor
public enum LoginType {
	/**
	 * 用户名
	 */
	USERNAME("username", 1),

	/**
	 * 手机号码
	 */
	MOBILE("mobile", 2);
	/**
	 * 名称
	 */
	final String name;
	/**
	 * 类型
	 */
	final int type;
}
