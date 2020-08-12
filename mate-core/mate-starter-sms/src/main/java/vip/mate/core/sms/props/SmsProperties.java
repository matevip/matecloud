package vip.mate.core.sms.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix= SmsProperties.PREFIX )
public class SmsProperties {

    public static final String PREFIX="sms.ali";

    /**
     * 短信API产品名称
     */
    private String product = "Dysmsapi";

    /**
     * 短信API产品域名
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * 区域标识
     */
    private String regionId = "cn-hangzhou";

    /**
     * 是否可用
     */
    private boolean enable;

    /**
     * accessKeyId
     */
    private String accessKey;

    /**
     * accessSecret
     */
    private String secretKey;

    /**
     * 短信模板ID
     */
    private String templateId;

    /**
     * 短信签名
     */
    private String signName;

}
