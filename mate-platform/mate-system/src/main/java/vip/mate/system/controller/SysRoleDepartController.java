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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysRoleDepart;
import vip.mate.system.service.ISysRoleDepartService;

import javax.validation.Valid;

/**
 * <p>
 * 角色和部门关联表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2021-04-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-role-depart")
@Tag(name  = "角色和部门关联表", description = "角色和部门关联表接口")
public class SysRoleDepartController extends BaseController {

    private final ISysRoleDepartService sysRoleDepartService;

    /**
     * 分页列表
     *
     * @param search 　搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "角色和部门关联表列表", exception = "角色和部门关联表列表请求异常")
    @GetMapping("/page")
    @Operation(summary = "角色和部门关联表列表", description = "分页查询")
    @Parameters({
        @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
        @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
        @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
        @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
        @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> page(Search search) {
		return Result.data(sysRoleDepartService.listPage(search));
    }

    /**
     * 角色和部门关联表信息
     *
     * @param id Id
     * @return Result
     */
    @PreAuth
    @Log(value = "角色和部门关联表信息", exception = "角色和部门关联表信息请求异常")
    @GetMapping("/get")
    @Operation(summary = "角色和部门关联表信息", description = "根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "ID", in = ParameterIn.DEFAULT),
    })
    public Result<?> get(@RequestParam String id) {
		return Result.data(sysRoleDepartService.getById(id));
	}

    /**
    * 角色和部门关联表设置
    *
    * @param sysRoleDepart SysRoleDepart 对象
    * @return Result
    */
    @PreAuth
    @Log(value = "角色和部门关联表设置", exception = "角色和部门关联表设置请求异常")
    @PostMapping("/set")
    @Operation(summary = "角色和部门关联表设置", description = "角色和部门关联表设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysRoleDepart sysRoleDepart) {
		return Result.condition(sysRoleDepartService.saveOrUpdate(sysRoleDepart));
	}

    /**
    * 角色和部门关联表删除
    *
    * @param ids id字符串，根据,号分隔
    * @return Result
    */
    @PreAuth
    @Log(value = "角色和部门关联表删除", exception = "角色和部门关联表删除请求异常")
    @PostMapping("/del")
    @Operation(summary = "角色和部门关联表删除", description = "角色和部门关联表删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    public Result<?> del(@RequestParam String ids) {
		return Result.condition(sysRoleDepartService.removeByIds(CollectionUtil.stringToCollection(ids)));
	}
}

