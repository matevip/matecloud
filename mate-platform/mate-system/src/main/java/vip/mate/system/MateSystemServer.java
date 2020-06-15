package vip.mate.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * 管理系统后台启动类
 * @author xzf
 */
@EnableFeignClients(basePackages = "vip.mate.*")
@SpringBootApplication
public class MateSystemServer {
    public static void main(String[] args) {
        SpringApplication.run(MateSystemServer.class, args);
    }
}
