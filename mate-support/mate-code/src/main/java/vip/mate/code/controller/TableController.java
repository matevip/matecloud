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
package vip.mate.code.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.util.CollectionUtil;

import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.web.controller.BaseController;
import vip.mate.code.service.ITableService;
import vip.mate.code.entity.Table;
import javax.validation.Valid;

/**
 * <p>
 * 代码生成基础表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2022-03-21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/table")
@Api(value = "代码生成基础表", tags = "代码生成基础表接口")
public class TableController extends BaseController {

    private final ITableService tableService;

    /**
     * 分页列表
     *
     * @param search 　搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "代码生成基础表列表", exception = "代码生成基础表列表请求异常")
    @GetMapping("/page")
    @ApiOperation(value = "代码生成基础表列表", notes = "分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
        @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
        @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
        @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
        @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> page(Search search) {
		return Result.data(tableService.listPage(search));
    }

    /**
     * 代码生成基础表信息
     *
     * @param id Id
     * @return Result
     */
    @PreAuth
    @Log(value = "代码生成基础表信息", exception = "代码生成基础表信息请求异常")
    @GetMapping("/get")
    @ApiOperation(value = "代码生成基础表信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "ID", paramType = "form"),
    })
    public Result<?> get(@RequestParam String id) {
		return Result.data(tableService.getById(id));
	}

    /**
    * 代码生成基础表设置
    *
    * @param table Table 对象
    * @return Result
    */
    @PreAuth
    @Log(value = "代码生成基础表设置", exception = "代码生成基础表设置请求异常")
    @PostMapping("/set")
    @ApiOperation(value = "代码生成基础表设置", notes = "代码生成基础表设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody Table table) {
		return Result.condition(tableService.saveOrUpdate(table));
	}

    /**
    * 代码生成基础表删除
    *
    * @param ids id字符串，根据,号分隔
    * @return Result
    */
    @PreAuth
    @Log(value = "代码生成基础表删除", exception = "代码生成基础表删除请求异常")
    @PostMapping("/del")
    @ApiOperation(value = "代码生成基础表删除", notes = "代码生成基础表删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> del(@RequestParam String ids) {
		return Result.condition(tableService.removeByIds(CollectionUtil.stringToCollection(ids)));
	}
}

