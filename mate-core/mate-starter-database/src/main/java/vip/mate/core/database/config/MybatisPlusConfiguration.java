package vip.mate.core.database.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.common.factory.YamlPropertySourceFactory;
import vip.mate.core.database.handler.MateMetaObjectHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@EnableTransactionManagement
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-db.yml")
@MapperScan("vip.mate.**.mapper.**")
public class MybatisPlusConfiguration {

    /**
     *分页查询拦截器
     */
    @Bean
    @Order(-2)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

//    @Bean
//    public GlobalConfig globalConfig() {
//        GlobalConfig globalConfig = new GlobalConfig();
//        globalConfig.setMetaObjectHandler(new MateMetaObjectHandler());
//        return globalConfig;
//    }
    /**
     * 自动填充数据
     */
    @Bean
    @ConditionalOnMissingBean(MateMetaObjectHandler.class)
    public MateMetaObjectHandler mateMetaObjectHandler(){
        MateMetaObjectHandler metaObjectHandler = new MateMetaObjectHandler();
        log.info("MateMetaObjectHandler [{}]", metaObjectHandler);
        return metaObjectHandler;
    }
}
