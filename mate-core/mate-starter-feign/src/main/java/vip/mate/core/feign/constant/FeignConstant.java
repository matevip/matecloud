package vip.mate.core.feign.constant;

import lombok.experimental.UtilityClass;

/**
 * Feign常量类
 * @author xuzhanfu
 * @Date 2020-7-1
 */
@UtilityClass
public class FeignConstant {

    /**
     * 网关
     */
    public final String MATE_CLOUD_GATEWAY = "mate-gateway";

    /**
     * 系统服务
     */
    public final String MATE_CLOUD_SYSTEM = "mate-system";

    /**
     * 认证服务
     */
    public final String MATE_CLOUD_UAA = "mate-uaa";

    /**
     * 消息生产者
     */
    public final String MATE_CLOUD_LOG_PRODUCER = "mate-log-producer";
}
