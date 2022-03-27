package vip.mate.code.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.mate.code.entity.Table;
import vip.mate.code.mapper.TableMapper;
import vip.mate.code.vo.ColumnInfoVO;
import vip.mate.code.vo.TableInfoVO;

import java.util.List;

@RequiredArgsConstructor
@DS("#dsName")
@Service
public class TableInfoService {

    private final TableMapper tableMapper;

    public IPage<TableInfoVO> queryPage(IPage page, String tableName, String dsName) {
        return tableMapper.selectByPage(page, tableName);
    }

    /**
     * 根据表名查询对应表信息
     * @param tableName 表名
     * @return TableInfo
     */
    public TableInfoVO queryTableInfo(String tableName, String dsName) {
        return tableMapper.queryTableInfo(tableName);
    }

    /**
     * 查询指定表的列信息
     * @param tableName 表名
     * @return List<ColumnInfo>
     */
    public List<ColumnInfoVO> listColumnInfo(String tableName, String dsName) {
        return tableMapper.listColumnInfo(tableName);
    }

}
