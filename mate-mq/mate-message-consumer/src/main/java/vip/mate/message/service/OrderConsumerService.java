package vip.mate.message.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.mate.core.rocketmq.channel.MateSink;
import vip.mate.core.rocketmq.entity.Order;

/**
 * 消息订单消息
 *
 * @author pangu
 */
@Slf4j
@Service
public class OrderConsumerService {

	@StreamListener(MateSink.ORDER_MESSAGE_INPUT)
	@Transactional(rollbackFor = Exception.class)
	public void orderHandle(@Payload Order order) {
		log.error("接收到的订单消息为:{}", order);
	}
}
