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
package vip.mate.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.core.database.entity.Search;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysApi;
import vip.mate.system.mapper.SysApiMapper;
import vip.mate.system.service.ISysApiService;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 系统接口表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-10-14
 */
@Service
public class SysApiServiceImpl extends ServiceImpl<SysApiMapper, SysApi> implements ISysApiService {

    @Override
    public IPage<SysApi> listPage(Page page, Search search, String serviceId) {
        LambdaQueryWrapper<SysApi> queryWrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(search.getStartDate())) {
            queryWrapper.between(SysApi::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StrUtil.isNotBlank(search.getKeyword())) {
            queryWrapper.and(i -> i
                    .or().like(SysApi::getCode, search.getKeyword())
                    .or().like(SysApi::getPath, search.getKeyword()));
        }
        if (StrUtil.isNotBlank(serviceId)) {
            queryWrapper.eq(SysApi::getServiceId, serviceId);
        }
        queryWrapper.orderByDesc(SysApi::getId);
        return this.baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public SysApi getByCode(String code) {
        LambdaQueryWrapper<SysApi> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysApi::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean status(String ids, String status) {
        Collection<? extends Serializable> collection = CollectionUtil.stringToCollection(ids);

        for (Object id : collection) {
            SysApi sysApi = this.baseMapper.selectById(CollectionUtil.objectToLong(id, 0L));
            sysApi.setStatus(status);
            this.baseMapper.updateById(sysApi);
        }
        return true;
    }
}
