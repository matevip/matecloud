package vip.mate.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class MateAdminServer {
    public static void main(String[] args) {
        SpringApplication.run(MateAdminServer.class, args);
    }
}
