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
package vip.mate.code.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import vip.mate.code.entity.Table;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.mate.code.vo.ColumnInfoVO;
import vip.mate.code.vo.TableInfoVO;

import java.util.List;

/**
 * <p>
 * 代码生成基础表 Mapper 接口
 * </p>
 *
 * @author pangu
 * @since 2022-03-21
 */
public interface TableMapper extends BaseMapper<Table> {

    /**
     * 分页查询表格
     *
     * @param page      分页参数
     * @param tableName 表名
     * @return
     */
    IPage<TableInfoVO> selectByPage(IPage<?> page, @Param("tableName") String tableName);

    /**
     * 从数据库内查询表信息
     *
     * @param tableName
     * @return
     */
    TableInfoVO queryTableInfo(@Param("tableName") String tableName);

    /**
     * 查询列信息
     * @param tableName 表名
     * @return List<ColumnInfo>
     */
    List<ColumnInfoVO> listColumnInfo(@Param("tableName") String tableName);

}
