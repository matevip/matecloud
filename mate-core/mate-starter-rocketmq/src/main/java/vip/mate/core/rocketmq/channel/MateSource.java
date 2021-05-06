package vip.mate.core.rocketmq.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import vip.mate.core.rocketmq.constant.MessageConstant;

/**
 * 生产者Channel
 * 3.0.8+版本不再使用
 *
 * @author xuzhanfu
 */
@Deprecated
public interface MateSource {

	/**
	 * 短消息通道
	 *
	 * @return MessageChannel
	 */
	@Output(MessageConstant.SMS_MESSAGE_OUTPUT)
	MessageChannel smsOutput();

	/**
	 * 邮件通道
	 *
	 * @return MessageChannel
	 */
	@Output(MessageConstant.EMAIL_MESSAGE_OUTPUT)
	MessageChannel emailOutput();

	/**
	 * 订单通道
	 *
	 * @return MessageChannel
	 */
	@Output(MessageConstant.ORDER_MESSAGE_OUTPUT)
	MessageChannel orderOutput();
}
