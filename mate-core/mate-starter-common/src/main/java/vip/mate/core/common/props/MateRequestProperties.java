package vip.mate.core.common.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@Getter
@Setter
@RefreshScope
@ConfigurationProperties("mate.request")
public class MateRequestProperties {

    /**
     * 是否开启日志链路追踪
     */
    private Boolean isTraceId = false;

    /**
     * 是否启用获取IP地址
     */
    private Boolean isGetIp = false;
}
