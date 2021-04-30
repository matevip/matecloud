package vip.mate.core.rocketmq.constant;

import vip.mate.core.common.util.StringPool;

/**
 * 消息中心常量
 *
 * @author pangu
 */
public class MessageConstant {

	/**
	 * 生产者服务名称
	 */
	public static final String MESSAGE_PRODUCER_SERVICE = "mate-message-producer";

	/**
	 * 消费者服务名称
	 */
	public static final String MESSAGE_CONSUMER_SERVICE = "mate-message-consumer";


	/**
	 * 短信消息
	 */
	public static final String SMS_MESSAGE = "sms" + StringPool.DASH;

	/**
	 * 邮件消息
	 */
	public static final String EMAIL_MESSAGE = "email" + StringPool.DASH;

	/**
	 * 订单消息
	 */
	public static final String ORDER_MESSAGE = "order" + StringPool.DASH;

	/**
	 * 生产者标识
	 */
	public static final String OUTPUT = "out" + StringPool.DASH + StringPool.ZERO;

	/**
	 * 消费者标识
	 */
	public static final String INPUT = "in" + StringPool.DASH + StringPool.ZERO;

	/**
	 * 消息生产者
	 */
	public static final String SMS_MESSAGE_OUTPUT = SMS_MESSAGE + OUTPUT;

	/**
	 * 邮件生产者
	 */
	public static final String EMAIL_MESSAGE_OUTPUT = EMAIL_MESSAGE + OUTPUT;

	/**
	 * 订单生产者
	 */
	public static final String ORDER_MESSAGE_OUTPUT = ORDER_MESSAGE + OUTPUT;

	/**
	 * 短信消费者
	 */
	public static final String SMS_MESSAGE_INPUT = SMS_MESSAGE + INPUT;

	/**
	 * 邮件消费者
	 */
	public static final String EMAIL_MESSAGE_INPUT = EMAIL_MESSAGE + INPUT;

	/**
	 * 订单消费者
	 */
	public static final String ORDER_MESSAGE_INPUT = ORDER_MESSAGE + INPUT;

	/**
	 * 订单组
	 */
	public static final String ORDER_BINDER_GROUP = ORDER_MESSAGE + "binder-group";


}
