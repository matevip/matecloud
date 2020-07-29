package vip.mate.core.gray.api;

import java.util.Map;

/**
 * 灰度负载过滤上下文接口
 * @author pangu
 * @since 2020-7-26
 */
public interface RibbonFilterContext {

    RibbonFilterContext add(String key, String value);

    String get(String key);

    RibbonFilterContext remove(String key);

    Map<String, String> getAttributes();

}
