package vip.mate.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*
 * 管理系统后台启动类
 * @author xzf
 */

@EnableFeignClients(basePackages = "vip.mate.*")
@SpringBootApplication
@MapperScan({"vip.mate.admin.mapper"})
public class MateAdminApp {
    public static void main(String[] args) {
        SpringApplication.run(MateAdminApp.class, args);
    }
}
