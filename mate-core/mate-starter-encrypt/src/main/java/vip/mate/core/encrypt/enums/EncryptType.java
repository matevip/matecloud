package vip.mate.core.encrypt.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 加密类型枚举类
 *
 * @author pangu
 */
@Getter
@AllArgsConstructor
public enum EncryptType {

	/**
	 * 基于Base64加密方式
	 */
	BASE64("base64"),
	/**
	 * 自定义加密方式
	 */
	CUSTOM("自定义"),
	/**
	 * AES对称加密
	 */
	AES("对称加密,需指定秘钥"),
	/**
	 * RSA非对称加密
	 */
	RSA("非对称加密,需指定公钥和私钥");

	private String describe;
}
