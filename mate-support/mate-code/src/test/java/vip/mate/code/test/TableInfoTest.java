package vip.mate.code.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vip.mate.code.service.ITableService;
import vip.mate.code.service.TableInfoService;
import vip.mate.code.vo.ColumnInfoVO;
import vip.mate.code.vo.TableInfoVO;

import java.util.List;

@Slf4j
@SpringBootTest
public class TableInfoTest {

    @Autowired
    private TableInfoService tableInfoService;

    @Autowired
    private ITableService tableService;

    /**
     * 测试指定数据源的分页表数据
     */
    @Test
    void getTableInfoPageTest() {
        IPage page = new Page();
        IPage<TableInfoVO> localdb = tableInfoService.queryPage(page, "", "localdb");

        log.info("tableInfo: {}", JSONObject.toJSONString(localdb));
    }

    @Test
    void getColumnInfoTest() {
        List<ColumnInfoVO> columnInfoVOS = tableInfoService.listColumnInfo("mate_sys_user", "master");
        log.info("columnInfo: {}", JSONObject.toJSONString(columnInfoVOS));
    }

    @Test
    void getTableTest() {
        tableService.initTable("mate_sys_user", "master");
    }
}
