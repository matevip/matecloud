package vip.mate.seata.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.feign.annotation.EnableMateFeign;

/**
 * Seata订单启动类
 *
 * @author pangu
 */
@EnableMateFeign
@SpringBootApplication
@EnableTransactionManagement
public class SeataOrderServer {
	public static void main(String[] args) {
		SpringApplication.run(SeataOrderServer.class, args);
	}
}
