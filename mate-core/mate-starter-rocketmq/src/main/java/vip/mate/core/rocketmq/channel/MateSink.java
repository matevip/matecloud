package vip.mate.core.rocketmq.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import vip.mate.core.rocketmq.constant.MessageConstant;

/**
 * 消费者Channel
 * 3.0.8+版本不再使用
 * @author pangu
 */
@Deprecated
public interface MateSink {

	String SMS_MESSAGE_INPUT = MessageConstant.SMS_MESSAGE_INPUT;

	String EMAIL_MESSAGE_INPUT = MessageConstant.EMAIL_MESSAGE_INPUT;

	String ORDER_MESSAGE_INPUT = MessageConstant.ORDER_MESSAGE_INPUT;

	/**
	 * 短消息消费者
	 *
	 * @return SubscribableChannel
	 */
	@Input(SMS_MESSAGE_INPUT)
	SubscribableChannel smsInput();

	/**
	 * 邮件消费者
	 *
	 * @return SubscribableChannel
	 */
	@Input(EMAIL_MESSAGE_INPUT)
	SubscribableChannel emailInput();

	/**
	 * 订单消费者
	 *
	 * @return SubscribableChannel
	 */
	@Input(ORDER_MESSAGE_INPUT)
	SubscribableChannel orderInput();
}
