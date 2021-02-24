package vip.mate.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 代码生成增强版本启动类
 *
 * @author pangu
 * @since 2.3.8
 */
@SpringBootApplication
@MapperScan({"vip.mate.generator.mapper"})
public class MateGeneratorServer {
	public static void main(String[] args) {
		SpringApplication.run(MateGeneratorServer.class, args);
	}
}
