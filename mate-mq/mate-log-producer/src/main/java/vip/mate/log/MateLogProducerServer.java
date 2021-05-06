package vip.mate.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import vip.mate.core.feign.annotation.EnableMateFeign;

/**
 * 日志消息生产者启动类
 * @author pangu
 */
@EnableMateFeign
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class MateLogProducerServer {

    public static void main(String[] args) {
        SpringApplication.run(MateLogProducerServer.class, args);
    }

}
