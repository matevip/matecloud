package vip.mate.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import vip.mate.core.feign.annotation.EnableMateFeign;

/*
 * 管理系统后台启动类
 * @author xzf
 */
@EnableMateFeign
@SpringBootApplication
//@ComponentScan(value="vip.mate.core.auth")
//@ComponentScan(value="vip.mate.system")
public class MateSystemServer {
    public static void main(String[] args) {
        SpringApplication.run(MateSystemServer.class, args);
    }
}
