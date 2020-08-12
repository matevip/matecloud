package vip.mate.core.sms.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.sms.core.AliSmsTemplate;
import vip.mate.core.sms.core.SmsTemplate;
import vip.mate.core.sms.props.SmsProperties;

@Configuration
@EnableConfigurationProperties(value = SmsProperties.class)
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "enable", havingValue = "true")
public class AliSmsConfiguration {

    public SmsTemplate aliSmsTemplate(SmsProperties smsProperties) {
        return new AliSmsTemplate(smsProperties);
    }
}
