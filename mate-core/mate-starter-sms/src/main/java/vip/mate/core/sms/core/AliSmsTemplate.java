package vip.mate.core.sms.core;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import vip.mate.core.sms.props.SmsProperties;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class AliSmsTemplate implements SmsTemplate {

    private final SmsProperties smsProperties;

    /**
     * 获取默认客户端
     *
     * @return
     */
    public IAcsClient getDefaultAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(smsProperties.getRegionId(), smsProperties.getAccessKey(), smsProperties.getSecretKey());
        DefaultProfile.addEndpoint(smsProperties.getRegionId(),
                smsProperties.getProduct(), smsProperties.getDomain());
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        return new DefaultAcsClient(profile);
    }

    /**
     * 封装公共的request
     * @return
     */
    private CommonRequest request(){
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(smsProperties.getDomain());
        request.setSysVersion(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        return request;
    }

    @Override
    public boolean sendSms(@NonNull String phoneNumber, @NonNull String signName, @NonNull String templateCode, @NonNull String templateParam) {
        CommonRequest request = this.request();
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = this.getDefaultAcsClient().getCommonResponse(request);
            log.info(response.getData());
            return true;
        } catch (Exception e) {
            log.error("异常：{}", e);
        }
        return false;
    }

    @Override
    public String sendRandCode(int digits) {
        StringBuilder sBuilder = new StringBuilder();
        Random rd = new Random( LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        for(int i = 0; i < digits; ++i) {
            sBuilder.append(rd.nextInt(9));
        }
        return sBuilder.toString();
    }
}
