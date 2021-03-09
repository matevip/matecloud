package vip.mate.core.database.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 序列化和反序列化配置
 *
 * @author pangu
 * @link https://www.cnblogs.com/asker009/p/12888388.html
 */
@Configuration
public class JacksonConfiguration {

	/**
	 * Jackson全局转化long类型为String
	 * 解决jackson序列化时传入前端Long类型缺失精度问题
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		Jackson2ObjectMapperBuilderCustomizer cunstomizer = new Jackson2ObjectMapperBuilderCustomizer() {
			@Override
			public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
				jacksonObjectMapperBuilder.serializerByType(BigInteger.class, ToStringSerializer.instance);
				jacksonObjectMapperBuilder.serializerByType(BigDecimal.class, ToStringSerializer.instance);
				jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
				// jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
			}
		};
		return cunstomizer;
	}
}
