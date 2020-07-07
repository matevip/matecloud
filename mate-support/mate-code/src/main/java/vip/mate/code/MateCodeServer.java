package vip.mate.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成启动类
 *
 * @author xuzhanfu
 * @date 2019-10-09 15:06
 **/
@SpringBootApplication
public class MateCodeServer {
    public static void main(String[] args) {
        SpringApplication.run(MateCodeServer.class, args);
    }
}