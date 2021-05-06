package vip.mate.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 消息中心生产者启动类
 *
 * @author pangu
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MateMessageProducerServer {
	public static void main(String[] args) {
		SpringApplication.run(MateMessageProducerServer.class, args);
	}
}
