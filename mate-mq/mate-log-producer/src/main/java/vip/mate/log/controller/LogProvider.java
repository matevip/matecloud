package vip.mate.log.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.kafka.channel.LogChannel;
import vip.mate.log.entity.LogMessage;

@RestController
@AllArgsConstructor
public class LogProvider {

    private final LogChannel logChannel;

    @GetMapping("/sendMessage")
    public boolean sendMessage(LogMessage logMessage) {
        return logChannel.sendLogMessage().send(MessageBuilder.withPayload(logMessage).build());
    }

}
