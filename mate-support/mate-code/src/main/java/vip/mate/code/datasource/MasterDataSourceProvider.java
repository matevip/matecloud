package vip.mate.code.datasource;

import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import vip.mate.code.constant.DataSourceConstant;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MasterDataSourceProvider implements DynamicDataSourceProvider {

    /**
     * JDBC url 地址
     */
    private final String url;

    /**
     * JDBC 用户名
     */
    private final String username;

    /**
     * JDBC 密码
     */
    private final String password;


    /**
     * 数据源创建器
     */
    private final DefaultDataSourceCreator defaultDataSourceCreator;

    public MasterDataSourceProvider(DataSourceProperties dataSourceProperties,
                                    DefaultDataSourceCreator dataSourceCreator) {
        this.url = dataSourceProperties.getUrl();
        this.username = dataSourceProperties.getUsername();
        this.password = dataSourceProperties.getPassword();
        this.defaultDataSourceCreator = dataSourceCreator;
    }

    /**
     * 加载主数据源
     *
     * @return
     */
    @Override
    public Map<String, DataSource> loadDataSources() {
        // 添加主数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>(8);
        DataSourceProperty masterDataSourceProperty = new DataSourceProperty();
        masterDataSourceProperty.setUsername(username);
        masterDataSourceProperty.setPassword(password);
        masterDataSourceProperty.setUrl(url);
        String dsName = DataSourceConstant.DEFAULT_DS_NAME;
        String poolName = masterDataSourceProperty.getPoolName();
        if (poolName == null || "".equals(poolName)) {
            poolName = dsName;
        }
        masterDataSourceProperty.setPoolName(poolName);
        DataSource dataSource = defaultDataSourceCreator.createDataSource(masterDataSourceProperty);
        dataSourceMap.put(dsName, dataSource);
        return dataSourceMap;
    }
}
