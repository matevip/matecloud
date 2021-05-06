package vip.mate.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 消息中心消费者启动类
 *
 * @author pangu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MateMessageConsumerServer {
	public static void main(String[] args) {
		SpringApplication.run(MateMessageConsumerServer.class, args);
	}
}
