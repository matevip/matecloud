package vip.mate.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import vip.mate.core.rocketmq.channel.MateSource;

/**
 * 消息中心生产者启动类
 *
 * @author pangu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableBinding({MateSource.class})
public class MateMessageProducerServer {
	public static void main(String[] args) {
		SpringApplication.run(MateMessageProducerServer.class, args);
	}
}
