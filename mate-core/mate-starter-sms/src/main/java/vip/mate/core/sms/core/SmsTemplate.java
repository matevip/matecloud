package vip.mate.core.sms.core;

import lombok.NonNull;

/**
 * 短信通用操作
 *
 * @author pangu
 */
public interface SmsTemplate {


	/**
	 * 发送短消息
	 *
	 * @param phoneNumber   手机号
	 * @param signName      　签名
	 * @param templateCode  　模板Id
	 * @param templateParam JSON模板参数字符串
	 * @return boolean
	 */
	boolean sendSms(@NonNull String phoneNumber, @NonNull String signName,
	                @NonNull String templateCode, @NonNull String templateParam);

	/**
	 * 发送验证码
	 *
	 * @param digits 位数
	 * @return String
	 */
	String sendRandCode(int digits);
}
