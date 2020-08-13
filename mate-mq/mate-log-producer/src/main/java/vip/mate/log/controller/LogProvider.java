package vip.mate.log.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.kafka.channel.LogChannel;
import vip.mate.log.entity.LogMessage;

@Slf4j
@RestController
@AllArgsConstructor
public class LogProvider {

    private final LogChannel logChannel;

    @GetMapping("/sendMessage")
    public boolean sendMessage(LogMessage logMessage) {
        return logChannel.sendLogMessage().send(MessageBuilder.withPayload(logMessage).build());
    }


    @GetMapping("/sendMessageStr")
    public boolean sendMessage(String message) {
        return logChannel.sendLogMessage().send(MessageBuilder.withPayload(message).build());
    }

    @StreamListener(LogChannel.LOG_INPUT)
    public void receive(Message<String> message) {
        log.error(message.getPayload());
    }

}
