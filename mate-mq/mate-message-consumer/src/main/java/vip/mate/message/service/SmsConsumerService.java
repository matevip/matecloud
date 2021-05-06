package vip.mate.message.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

/**
 * 短信消费者业务
 *
 * @author xuzhanfu
 */
@Slf4j
@Service
public class SmsConsumerService {

	/**
	 * 函数式编辑接收消息
	 *
	 * @return
	 */
	@Bean
	public Consumer<String> sms() {
		return message -> {
			log.info("接收的普通消息为：{}", message);
		};
	}
}
