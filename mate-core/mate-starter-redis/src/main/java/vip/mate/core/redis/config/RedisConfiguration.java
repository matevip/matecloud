package vip.mate.core.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import vip.mate.core.redis.props.MateRedisProperties;
import vip.mate.core.redis.core.RedisService;
import vip.mate.core.redis.util.RedisLockUtil;

/**
 * Redis基础配置类
 *
 * @author pangu
 */
@Configuration
@EnableConfigurationProperties(MateRedisProperties.class)
@ConditionalOnProperty(value = MateRedisProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class RedisConfiguration {

	@Bean
	public RedisSerializer<String> redisKeySerializer() {
		return RedisSerializer.string();
	}

	@Bean
	public RedisSerializer<Object> redisValueSerializer() {
		return RedisSerializer.json();
	}

	@SuppressWarnings("all")
	@Bean(name = "redisTemplate")
	@ConditionalOnClass(RedisOperations.class)
	public org.springframework.data.redis.core.RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		org.springframework.data.redis.core.RedisTemplate template = new org.springframework.data.redis.core.RedisTemplate();
		template.setConnectionFactory(factory);

		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
//		ObjectMapper om = new ObjectMapper();
		ObjectMapper om = new Jackson2ObjectMapperBuilder()
				.locale(Locale.CHINA)
				.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
				.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.serializerByType(Long.class, ToStringSerializer.instance)
				.modules(new MateJavaTimeModule())
				.build();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisService redisService() {
		return new RedisService();
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisLockUtil redisLockUtil(RedisTemplate redisTemplate) {
		return new RedisLockUtil(redisTemplate);
	}

	public static class MateJavaTimeModule extends SimpleModule {

		public MateJavaTimeModule() {
			super(PackageVersion.VERSION);
			this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
			this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
		}

	}
}
