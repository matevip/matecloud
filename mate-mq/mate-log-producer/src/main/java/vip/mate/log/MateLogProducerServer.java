package vip.mate.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.stream.annotation.EnableBinding;
import vip.mate.core.feign.annotation.EnableMateFeign;
import vip.mate.core.kafka.channel.LogChannel;

/**
 * 日志消息生产者启动类
 * @author pangu
 */
@EnableMateFeign
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableBinding(LogChannel.class)
public class MateLogProducerServer {

    public static void main(String[] args) {
        SpringApplication.run(MateLogProducerServer.class, args);
    }

}
