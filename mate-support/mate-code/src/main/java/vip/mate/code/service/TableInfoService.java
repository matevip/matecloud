package vip.mate.code.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.mate.code.constant.CodeConstant;
import vip.mate.code.entity.Column;
import vip.mate.code.entity.Table;
import vip.mate.code.mapper.TableMapper;
import vip.mate.code.util.CodeUtil;
import vip.mate.code.vo.ColumnInfoVO;
import vip.mate.code.vo.TableInfoVO;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@DS("#dsName")
@Service
public class TableInfoService {

    private final TableMapper tableMapper;
    private final IColumnService columnService;

    public IPage<TableInfoVO> queryPage(IPage page, String tableName, String dsName) {
        return tableMapper.selectByPage(page, tableName);
    }

    /**
     * 根据表名查询对应表信息
     *
     * @param tableName 表名
     * @return TableInfo
     */
    public TableInfoVO queryTableInfo(String tableName, String dsName) {
        return tableMapper.queryTableInfo(tableName);
    }

    /**
     * 查询指定表的列信息
     *
     * @param tableName 表名
     * @return List<ColumnInfo>
     */
    public List<ColumnInfoVO> listColumnInfo(String tableName, String dsName) {
        return tableMapper.listColumnInfo(tableName);
    }

    /**
     * 初始化表格和字段信息
     * <p>此初始化在编辑前完成，每次点击编辑时，都要执行一遍，
     * 会清理原数据，并重新插件，此步骤也可以理解为同步操作</p>
     *
     * @param tableName 表名
     * @param dsName    数据源名
     */
    @DSTransactional
    public void initTable(String tableName, String dsName) {
        // 查询表数据
        TableInfoVO tableInfoVO = this.queryTableInfo(tableName, dsName);
        // 拼装Table对象
        Table table = BeanUtil.copyProperties(tableInfoVO, Table.class);
        table.setName(tableInfoVO.getTableName());
        table.setComment(tableInfoVO.getTableComment());
        // 初始化表数据字段
        CodeUtil.initTableExt(table);
        // 检查数据如果存在，则删除之
        Long tableId = 0L;
        // 检查是数据是否存在，存在则删除之
        Table tableQuery = tableMapper.selectOne(Wrappers.<Table>lambdaQuery().eq(Table::getName, table.getName()));
        // 删除已存在的数据
        if (tableQuery != null) {
            tableId = tableQuery.getId();
            tableMapper.delete(Wrappers.<Table>lambdaQuery().eq(Table::getName, table.getName()));
        }
        // 插入表数据
        tableMapper.insert(table);
        // 开始查询字段数据
        List<ColumnInfoVO> columnInfos = this.listColumnInfo(tableName, dsName);
        // 格式化字段数据并返回Column对象
        List<Column> collect = columnInfos.parallelStream().map(columnInfo -> {
            Column column = new Column();
            column.setName(columnInfo.getColumnName());
            column.setComment(columnInfo.getColumnComment());
            column.setType(columnInfo.getDataType());
            column.setIsIncrement(columnInfo.getExtra().equalsIgnoreCase(CodeConstant.TABLE_AUTO_INCREMENT));
            column.setIsPk(columnInfo.getColumnKey().equals(CodeConstant.PRI));
            column.setIsRequired(columnInfo.getIsNullable().equalsIgnoreCase(CodeConstant.NO) && !columnInfo.getColumnKey().equalsIgnoreCase(CodeConstant.PRI));
            column.setIsUnique(columnInfo.getColumnKey().equalsIgnoreCase(CodeConstant.UNI));
            // 对数据进行再加工
            CodeUtil.initColumnField(column, table);
            return column;
        }).collect(Collectors.toList());
        // 查询数据是否存在，如存在，则删除之
        List<Column> list = columnService.list(Wrappers.<Column>lambdaQuery().eq(Column::getTableId, tableId));
        if (list != null) {
            columnService.remove(Wrappers.<Column>lambdaQuery().eq(Column::getTableId, tableId));
        }
        // 批量存储字段数据
        columnService.saveBatch(collect);
    }
}
