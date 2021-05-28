package vip.mate.message.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * 消息订单消息
 *
 * @author pangu
 */
@Slf4j
@Service
public class OrderConsumerService {


	/**
	 * 消费分布式事务消息
	 */
	@Bean
	public Consumer<String> order() {
		return order -> {
			log.info("接收的普通消息为：{}", order);
		};
	}

	/**
	 * 自定义全局异常处理
	 *
	 * @param message 消息体
	 */
	public void error(Message<?> message) {
		ErrorMessage errorMessage = (ErrorMessage) message;
		log.error("Handling ERROR, errorMessage = {} ", errorMessage);
	}

}
