package vip.mate.core.database.config;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import vip.mate.core.common.factory.YamlPropertySourceFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:mate-db.yml")
@MapperScan("vip.mate.**.mapper.**")
public class MybatisPlusConfiguration {

    /**
     * SQL输出拦截器
     * 只用于开发环境
     */
//    @Bean
//    public SqlExplainInterceptor sqlExplainInterceptor(){
//        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
//        List<ISqlParser> sqlParserList = new ArrayList<>();
//        sqlParserList.add(new BlockAttackSqlParser());
//        sqlExplainInterceptor.setSqlParserList(sqlParserList);
//        return sqlExplainInterceptor;
//    }
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
}
