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
package vip.mate.code.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.code.entity.Table;
import vip.mate.core.database.entity.Search;

import java.util.List;

/**
 * <p>
 * 代码生成基础表 服务类
 * </p>
 *
 * @author pangu
 * @since 2022-03-21
 */
public interface ITableService extends IService<Table> {

    /**
     * 分页业务方法
     *
     * @param search 　搜索参数
     * @return IPage
     */
    IPage<Table> listPage(Search search);

    /**
     * 初始化表格和字段信息
     * <p>此初始化在编辑前完成，每次点击编辑时，都要执行一遍，
     * 会清理原数据，并重新插件，此步骤也可以理解为同步操作</p>
     *
     * @param tableName 表名
     * @param dsName    数据源名
     */
    void initTable(String tableName, String dsName);

    /**
     * 预览代码
     *
     * @param tableName 表名
     * @return 数据列表
     */
    List<JSONObject> previewCode(String tableName);
}
