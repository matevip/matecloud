package vip.mate.log.feign;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.kafka.constant.LogConstant;
import vip.mate.core.log.feign.ICommonLogProvider;

/**
 * 消息生产者调用
 *
 * @author pangu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "调用消息生产者")
public class CommonLogProvider implements ICommonLogProvider {

	private final StreamBridge streamBridge;

	@Override
	@PostMapping("/provider/common-log/send")
	@Operation(summary = "发送普通消息", description = "发送普通消息")
	public Result<?> sendCommonLog(@RequestBody CommonLog commonLog) {
		boolean flag = streamBridge.send(LogConstant.LOG_OUTPUT, commonLog);
		if (flag) {
			return Result.success("操作成功");
		}
		return Result.fail("操作失败");
	}
}
