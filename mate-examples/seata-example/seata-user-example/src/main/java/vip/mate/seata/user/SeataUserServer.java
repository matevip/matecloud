package vip.mate.seata.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.feign.annotation.EnableMateFeign;

/**
 * Seata用户启动类
 *
 * @author pangu
 */
@EnableMateFeign
@SpringBootApplication
@EnableTransactionManagement
public class SeataUserServer {
	public static void main(String[] args) {
		SpringApplication.run(SeataUserServer.class, args);
	}
}
