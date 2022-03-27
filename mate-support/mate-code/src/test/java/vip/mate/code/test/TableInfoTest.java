package vip.mate.code.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.code.service.TableInfoService;
import vip.mate.code.vo.TableInfoVO;

import java.util.List;

@Slf4j
@SpringBootTest
public class TableInfoTest {

    @Autowired
    private TableInfoService tableInfoService;

    @Test
    void getTableInfoTest() {
        IPage page = new Page();
        IPage<TableInfoVO> master = tableInfoService.queryPage(page, "", "localdb");

        log.info("tableInfo: {}", JSONObject.toJSONString(master));
    }
}
