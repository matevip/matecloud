package vip.mate.code.datasource;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import vip.mate.code.entity.SysDataSource;
import vip.mate.code.service.ISysDataSourceService;

import java.util.List;

/**
 * 初始化动态数据源
 *
 * @author david
 * @since 2022-3-26
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DynamicDataSourceInit implements InitializingBean {

    private final ISysDataSourceService sysDataSourceService;

    private final DynamicDataSourceUtil dynamicDataSourceUtil;

    @Override
    public void afterPropertiesSet() throws Exception {

        List<SysDataSource> list = sysDataSourceService.list();

        for (SysDataSource sysDataSource : list) {
            String dsName = sysDataSource.getName();
            String username = sysDataSource.getUsername();
            String password = sysDataSource.getPassword();
            String url = sysDataSource.getUrl();
            DataSourceProperty dataSourceProperty = dynamicDataSourceUtil.setDataSourceProperty(dsName, url, username, password);
            log.debug("数据源是否可用：{}", dynamicDataSourceUtil.isAvailableDataSourceProperty(dataSourceProperty));
            if (dynamicDataSourceUtil.isAvailableDataSourceProperty(dataSourceProperty)) {
                dynamicDataSourceUtil.addDynamicDataSource(dataSourceProperty);
                log.info("数据源动态加载完成：{}", dsName);
            }

        }


    }
}
