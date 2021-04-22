package vip.mate.core.database.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 租户属性
 * @author xuzhanfu
 * @Date 2020-9-6
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties(prefix = "mate.tenant")
public class TenantProperties {

    /**
     * 是否开启租户模式
     */
    private Boolean enable = false;

    /**
     * 需要排除的多租户的表
     */
    private List<String> ignoreTables = Arrays.asList("mate_sys_menu","mate_sys_dict","mate_sys_client",
            "mate_sys_tenant", "mate_sys_role_permission","mate_sys_config","mate_sys_data_source","mate_sys_attachment");

    /**
     * 多租户字段名称
     */
    private String column = "tenant_id";

    /**
     * 排除不进行租户隔离的sql
     * 样例全路径：vip.mate.system.mapper.UserMapper.findList
     */
    private List<String> ignoreSqls = new ArrayList<>();
}
