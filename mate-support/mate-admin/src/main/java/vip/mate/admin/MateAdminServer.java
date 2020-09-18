package vip.mate.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Admin启动类
 *
 * @author pangu
 */
@EnableAdminServer
@SpringBootApplication
public class MateAdminServer {
	public static void main(String[] args) {
		SpringApplication.run(MateAdminServer.class, args);
	}
}
