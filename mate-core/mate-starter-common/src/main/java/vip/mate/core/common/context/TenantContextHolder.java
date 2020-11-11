package vip.mate.core.common.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 多租户Holder
 * @author pangu
 * @since 2020-9-8
 */
@UtilityClass
public class TenantContextHolder {

    /**
     * 支持父子线程之间的数据传递
     */
    private final ThreadLocal<String> THREAD_LOCAL_TENANT = new TransmittableThreadLocal<>();

    /**
     * TTL 设置租户ID<br/>
     * <b>谨慎使用此方法,避免嵌套调用。尽量使用 {@code TenantBroker} </b>
     * @param tenantId 租户ID
     */
    public void setTenantId(String tenantId) {
        THREAD_LOCAL_TENANT.set(tenantId);
    }

    /**
     * 获取TTL中的租户ID
     * @return String
     */
    public String getTenantId() {
        return THREAD_LOCAL_TENANT.get();
    }

    /**
     * 清除tenantId
     */
    public void clear() {
        THREAD_LOCAL_TENANT.remove();
    }
}
