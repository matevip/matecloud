package vip.mate.component.cache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.mate.component.service.ISysConfigService;
import vip.mate.core.oss.props.OssProperties;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@AllArgsConstructor
public class ConfigCache {

    private final ISysConfigService sysConfigService;

    @PostConstruct
    public void init() {
        log.error("此处加载配置文件");
        OssProperties oss = sysConfigService.getOssProperties();
    }
}
