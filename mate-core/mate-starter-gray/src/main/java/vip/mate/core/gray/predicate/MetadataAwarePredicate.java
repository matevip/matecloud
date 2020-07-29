package vip.mate.core.gray.predicate;

import com.alibaba.cloud.nacos.ribbon.NacosServer;
import vip.mate.core.gray.api.RibbonFilterContext;
import vip.mate.core.gray.support.RibbonFilterContextHolder;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 灰度元数据适配断言类
 * @author pangu
 * @since 2020-7-20
 */
public class MetadataAwarePredicate extends DiscoveryEnabledPredicate {

    @Override
    protected boolean apply(NacosServer server) {
        final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
        final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
        final Map<String, String> metadata = server.getMetadata();
        return metadata.entrySet().containsAll(attributes);
    }

}
