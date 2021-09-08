package vip.mate.system.test;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.system.service.ISysApiService;

/**
 * 业务单元测试示例
 *
 * @author matevip
 */
@Slf4j
@SpringBootTest
public class SysApiControllerTest {

    @Autowired
    private ISysApiService sysApiService;

    @Test
    void sync() {
        log.info("获取api列表:{}", JSON.toJSONString(sysApiService.list()));
    }
}
