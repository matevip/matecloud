package vip.mate.message.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import vip.mate.message.service.ISmsService;

/**
 * 发送短信实现类
 *
 * @author xuzhanfu
 */
@Service
@AllArgsConstructor
public class SmsServiceImpl implements ISmsService {

	private final StreamBridge streamBridge;

	@Override
	public void sendSms(String message) {
//		source.smsOutput().send(MessageBuilder.withPayload(message).build());
		streamBridge.send("send-out-0", message);
	}
}
