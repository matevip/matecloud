package vip.mate.core.dozer.config;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.mate.core.dozer.props.DozerProperties;
import vip.mate.core.dozer.util.DozerUtil;

import java.io.IOException;

/**
 * Dozer配置
 * @author pangu
 * @link http://dozer.sourceforge.net/documentation/usage.html
 * http://www.jianshu.com/p/bf8f0e8aee23
 */
@Configuration
@ConditionalOnClass({DozerBeanMapperFactoryBean.class, Mapper.class})
@ConditionalOnMissingBean(Mapper.class)
@EnableConfigurationProperties(DozerProperties.class)
public class DozerConfiguration {

    private final DozerProperties properties;


    /**
     * Constructor for creating auto configuration.
     *
     * @param properties properties
     */
    public DozerConfiguration(DozerProperties properties) {
        this.properties = properties;
    }

    @Bean
    public DozerUtil getDozerUtil(Mapper mapper) {
        return new DozerUtil(mapper);
    }

    /**
     * Creates default Dozer mapper
     *
     * @return Dozer mapper
     * @throws IOException if there is an exception during initialization.
     */
    @Bean
    public DozerBeanMapperFactoryBean dozerMapper() throws IOException {
        DozerBeanMapperFactoryBean factoryBean = new DozerBeanMapperFactoryBean();
        // 官方这样子写，没法用 匹配符！
        //factoryBean.setMappingFiles(properties.getMappingFiles());
        factoryBean.setMappingFiles(properties.resolveMapperLocations());
        return factoryBean;
    }
}
