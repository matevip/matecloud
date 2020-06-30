package vip.mate.core.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import vip.mate.core.web.datatype.MateJavaTimeModule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * 日期格式全局配置
 * @author pangu
 * @link https://juejin.im/post/5e940626e51d4546f5790979
 * @link https://blog.csdn.net/weixin_44600430/article/details/105512891
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class DateConverterConfiguration {

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    @Bean
    @Primary
    public ObjectMapper serializingObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new MateJavaTimeModule());
        return objectMapper;
    }

    /**
     * 自定义类型转换,HTTP请求日期字符串转换日期类型,
     * 相当于以前设置进 ConversionServiceFactoryBean
     *
     * @return Converter<java.lang.String,java.util.Date>
     */
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                try {
                    return parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("日期解析错误");
            }
        };
    }

    /**
     * 根据字符串进行解析,将Date转LocalDateTime
     *
     * @param source 日期字符串
     * @return java.time.LocalDateTime
     */
    public LocalDateTime parse(String source) throws ParseException {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        DateFormat format;
        source = source.trim();
        //判断是否yyyy-MM-dd格式
        if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            format = new SimpleDateFormat(YYYY_MM_DD);
            Date date = format.parse(source);
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
            //判断是否yyyy-MM-dd HH:mm:ss格式
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            Date date = format.parse(source);
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        }else {
            throw new IllegalArgumentException("Invalid false value " + source);
        }
    }

}
