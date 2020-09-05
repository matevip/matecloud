package vip.mate.log.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.dto.CommonLog;
import vip.mate.core.kafka.channel.LogChannel;

/**
 * @author xuzhanfu
 */
@Slf4j
@RestController
@AllArgsConstructor
public class LogProvider {

    private final LogChannel logChannel;

    @GetMapping("/sendMessageStr")
    public boolean sendMessage(String message) {
        return logChannel.sendLogMessage().send(MessageBuilder.withPayload(message).build());
    }

//    @StreamListener(LogChannel.LOG_INPUT)
//    public void receive(@Payload String message) {
//        log.info(String.format("consume: %s", message) + ",receive time:" +System.nanoTime());
//    }

    @StreamListener(LogChannel.LOG_INPUT)
    public void handler(@Payload CommonLog commonLog) {
        log.info(String.format("consume: %s", commonLog) + ",receive time:" +System.nanoTime());
    }

}
