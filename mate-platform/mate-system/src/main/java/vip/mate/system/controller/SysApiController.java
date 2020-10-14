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
package vip.mate.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysApi;
import vip.mate.system.service.ISysApiService;

import javax.validation.Valid;

/**
 * <p>
 * API管理 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-10-14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Api(value = "API管理", tags = "API管理接口")
public class SysApiController extends BaseController {

    private final ISysApiService sysApiService;

    /**
     * 分页列表
     *
     * @param page   分页信息
     * @param search 　搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "API列表", exception = "API管理列表请求异常")
    @GetMapping("/page")
    @ApiOperation(value = "API列表", notes = "分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
        @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
        @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
        @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
        @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> page(Page page, Search search) {
		return Result.data(sysApiService.listPage(page, search));
    }

    /**
     * API管理信息
     *
     * @param id Id
     * @return Result
     */
    @PreAuth
    @Log(value = "API信息", exception = "API管理信息请求异常")
    @GetMapping("/get")
    @ApiOperation(value = "API信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "ID", paramType = "form"),
    })
    public Result<?> get(@RequestParam String id) {
		return Result.data(sysApiService.getById(id));
	}

    /**
    * API管理设置
    *
    * @param sysApi SysApi 对象
    * @return Result
    */
    @PreAuth
    @Log(value = "API设置", exception = "API管理设置请求异常")
    @PostMapping("/set")
    @ApiOperation(value = "API设置", notes = "API设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysApi sysApi) {
		return Result.condition(sysApiService.saveOrUpdate(sysApi));
	}

    /**
    * API管理删除
    *
    * @param ids id字符串，根据,号分隔
    * @return Result
    */
    @PreAuth
    @Log(value = "API删除", exception = "API管理删除请求异常")
    @PostMapping("/del")
    @ApiOperation(value = "API删除", notes = "API删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> del(@RequestParam String ids) {
		return Result.condition(sysApiService.removeByIds(CollectionUtil.stringToCollection(ids)));
	}
}

