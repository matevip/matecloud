package vip.mate.seata.point;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.feign.annotation.EnableMateFeign;

/**
 * Seata积分启动类
 * @author pangu
 */
@EnableMateFeign
@SpringBootApplication
@EnableTransactionManagement
public class SeataPointServer {
	public static void main(String[] args) {
		SpringApplication.run(SeataPointServer.class, args);
	}
}
