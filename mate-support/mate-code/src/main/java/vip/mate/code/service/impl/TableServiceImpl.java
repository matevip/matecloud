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

import vip.mate.core.database.entity.Search;
import vip.mate.code.entity.Table;
import vip.mate.code.mapper.TableMapper;
import vip.mate.code.service.ITableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.util.PageUtil;

/**
 * <p>
 * 代码生成基础表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2022-03-21
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

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
}
