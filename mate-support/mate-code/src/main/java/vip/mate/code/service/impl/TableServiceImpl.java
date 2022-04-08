/*
 * Copyright 2020-2030, MateCloud, DAOTIANDI Technology Inc All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Author: pangu(7333791@qq.com)
 */
package vip.mate.code.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;
import vip.mate.code.constant.CodeConstant;
import vip.mate.code.entity.Column;
import vip.mate.code.entity.Table;
import vip.mate.code.mapper.TableMapper;
import vip.mate.code.service.IColumnService;
import vip.mate.code.service.ITableService;
import vip.mate.code.service.TableInfoService;
import vip.mate.code.util.CodeUtil;
import vip.mate.code.util.VmUtil;
import vip.mate.code.vo.ColumnInfoVO;
import vip.mate.code.vo.TableInfoVO;
import vip.mate.core.common.util.StringPool;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.database.util.PageUtil;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 代码生成基础表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2022-03-21
 */
@Service
@RequiredArgsConstructor
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    private final IColumnService columnService;
    private final TableInfoService tableInfoService;

    @Override
    public IPage<Table> listPage(Search search) {
        LambdaQueryWrapper<Table> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            queryWrapper.between(Table::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StringUtil.isNotBlank(search.getKeyword())) {
            queryWrapper.like(Table::getId, search.getKeyword());
        }
        queryWrapper.orderByDesc(Table::getCreateTime);
        return this.baseMapper.selectPage(PageUtil.getPage(search), queryWrapper);
    }

    @Override
    public void initTable(String tableName, String dsName) {
        // 查询表数据
        TableInfoVO tableInfoVO = tableInfoService.queryTableInfo(tableName, dsName);
        // 拼装Table对象
        Table table = BeanUtil.copyProperties(tableInfoVO, Table.class);
        table.setName(tableInfoVO.getTableName());
        table.setComment(tableInfoVO.getTableComment());
        // 初始化表数据字段
        CodeUtil.initTableExt(table);
        // 检查数据如果存在，则删除之
        Long tableId = 0L;
        // 检查是数据是否存在，存在则删除之
        Table tableQuery = this.getOne(Wrappers.<Table>lambdaQuery().eq(Table::getName, table.getName()));
        // 删除已存在的数据
        if (tableQuery != null) {
            tableId = tableQuery.getId();
            this.remove(Wrappers.<Table>lambdaQuery().eq(Table::getName, table.getName()));
        }
        // 插入表数据
        this.save(table);
        // 开始查询字段数据
        List<ColumnInfoVO> columnInfos = tableInfoService.listColumnInfo(tableName, dsName);
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

    @Override
    public List<JSONObject> previewCode(String tableName) {
        List<JSONObject> dataMap = new ArrayList<>();
        Table table = this.getOne(Wrappers.<Table>lambdaQuery().eq(Table::getName, tableName));
        if (ObjectUtil.isEmpty(table)) {
            // @Todo添加多数据源的部分代码
            initTable(tableName, "master");
            table = this.getOne(Wrappers.<Table>lambdaQuery().eq(Table::getName, tableName));
        }
        List<Column> columns = columnService.list(Wrappers.<Column>lambdaQuery().eq(Column::getTableId, table.getId()));
        // 初始化Velocity模板
        VmUtil.initVelocity();
        // 获取模板数据
        VelocityContext velocityContext = VmUtil.setupContext(table, columns);
        JSONObject data = null;
        // 模板列表
        List<String> templates = VmUtil.getTemplateList(null);
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, StringPool.UTF_8);
            tpl.merge(velocityContext, sw);
            data = new JSONObject();
            String vmName = StrUtil.subAfter(template, StrUtil.SLASH, true);
            data.put("name", vmName);
            data.put("language", StrUtil.subAfter(vmName, StrUtil.DOT, true));
            data.put("template", sw.toString());
            dataMap.add(data);
        }
        return dataMap;
    }
}
