//package vip.mate.message.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.messaging.support.ErrorMessage;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import vip.mate.core.rocketmq.channel.MateSink;
//import vip.mate.core.rocketmq.entity.Order;
//
///**
// * 消息订单消息
// *
// * @author pangu
// */
//@Slf4j
//@Service
//public class OrderConsumerService {
//
//
//	/**
//	 * 消费分布式事务消息
//	 *
//	 * @param order 　Order对象
//	 */
//	@StreamListener(MateSink.ORDER_MESSAGE_INPUT)
////	@Transactional(rollbackFor = Exception.class)
//	public void orderHandle(@Payload Order order) {
//		log.error("接收到的订单消息为:{}", order);
//	}
//
//	/**
//	 * 自定义全局异常处理
//	 *
//	 * @param message 消息体
//	 */
//	@StreamListener("errorChannel")
//	public void error(Message<?> message) {
//		ErrorMessage errorMessage = (ErrorMessage) message;
//		log.error("Handling ERROR, errorMessage = {} ", errorMessage);
//	}
//
//}
