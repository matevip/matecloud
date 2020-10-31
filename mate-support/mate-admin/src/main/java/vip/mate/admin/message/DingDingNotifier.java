package vip.mate.admin.message;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.taobao.api.ApiException;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

/**
 * 钉钉消息通知
 *
 * @author pangu
 */
@Slf4j
public class DingDingNotifier extends AbstractStatusChangeNotifier {

	/**
	 * 消息模板
	 */
	private static final String TEMPLATE = "服务名:%s(%s) n状态:%s(%s) n服务ip:%s";

	@Value("${spring.boot.admin.notify.dingding.token}")
	private String dingdingToken;

	public DingDingNotifier(InstanceRepository repository) {
		super(repository);
	}

	@Override
	protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
		return Mono.fromRunnable(() -> {

			if (event instanceof InstanceStatusChangedEvent) {
				log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
						((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());

				String status = ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus();
				String messageText = null;
				switch (status) {
					// 健康检查没通过
					case "DOWN":
						log.info("发送 健康检查没通过 的通知！");
						messageText = String.format(TEMPLATE, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "健康检查没通过", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					// 服务离线
					case "OFFLINE":
						log.info("发送 服务离线 的通知！");
						messageText = String.format(TEMPLATE, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务离线", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					//服务上线
					case "UP":
						log.info("发送 服务上线 的通知！");
						messageText = String.format(TEMPLATE, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务上线", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					// 服务未知异常
					case "UNKNOWN":
						log.info("发送 服务未知异常 的通知！");
						messageText = String.format(TEMPLATE, instance.getRegistration().getName(), event.getInstance(), ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus(), "服务未知异常", instance.getRegistration().getServiceUrl());
						this.sendMessage(messageText);
						break;
					default:
						break;
				}
			} else {
				log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
						event.getType());
			}
		});
	}

	/**
	 * 钉钉发送消息
	 *
	 * @param messageText 消息文本
	 */
	private void sendMessage(String messageText) {
		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=" + dingdingToken);
		OapiRobotSendRequest request = new OapiRobotSendRequest();
		request.setMsgtype("text");
		OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
		text.setContent(messageText);
		request.setText(text);

		try {
			client.execute(request);
		} catch (ApiException e) {
			log.info("[ERROR] sendMessage", e);
		}
	}
}
