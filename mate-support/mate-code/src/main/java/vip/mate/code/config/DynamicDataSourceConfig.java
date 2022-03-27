package vip.mate.code.config;

import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import vip.mate.code.datasource.MasterDataSourceProvider;
import vip.mate.code.vo.TableInfoVO;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig {

    @Bean
    public MasterDataSourceProvider masterDataSourceProvider(DataSourceProperties dataSourceProperties,
                                                             DefaultDataSourceCreator defaultDataSourceCreator) {
        return new MasterDataSourceProvider(dataSourceProperties, defaultDataSourceCreator);
    }
}
