package vip.mate.job.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RefreshScope
public class XxlJobConfiguration {

    @Value("${mate.job.admin.addresses}")
    private String adminAddresses;

    @Value("${mate.job.executor.appname}")
    private String appname;

    @Value("${mate.job.executor.address}")
    private String address;

    @Value("${mate.job.executor.ip}")
    private String ip;

    @Value("${mate.job.executor.port}")
    private int port;

    @Value("${mate.job.accessToken}")
    private String accessToken;

    @Value("${mate.job.executor.logpath}")
    private String logPath;

    @Value("${mate.job.executor.logretentiondays}")
    private int logRetentionDays;



    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
        xxlJobExecutor.setAdminAddresses(adminAddresses);
        xxlJobExecutor.setAppname(appname);
        xxlJobExecutor.setAddress(address);
        xxlJobExecutor.setIp(ip);
        xxlJobExecutor.setPort(port);
        xxlJobExecutor.setAccessToken(accessToken);
        xxlJobExecutor.setLogPath(logPath);
        xxlJobExecutor.setLogRetentionDays(logRetentionDays);

        return xxlJobExecutor;
    }

}
