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

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.code.dto.CodeConfig;
import vip.mate.code.entity.SysDataSource;
import vip.mate.code.service.ISysDataSourceService;
import vip.mate.code.util.GeneratorUtil;
import vip.mate.code.util.ZipUtil;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 代码管理控制类
 *
 * @author xuzhanfu
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/code")
@Api(tags = "代码管理")
public class SysCodeController {

    private final ISysDataSourceService sysDataSourceService;

    /**
     * 数据库表信息
     * @param dataSourceId 数据源Id
     * @return Result
     */
    @PreAuth
    @Log(value = "数据库表信息", exception = "数据库表信息请求异常")
    @ApiOperation(value = "数据库表信息", notes = "数据库表信息")
    @PostMapping("/table-list")
    public Result<List<TableInfo>> tableList(@RequestParam String dataSourceId) {
        SysDataSource sysDataSource = sysDataSourceService.getById(dataSourceId);
        GlobalConfig gc = new GlobalConfig();

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.getDbType(sysDataSource.getDbType()));
        dsc.setDriverName(sysDataSource.getDriverClass());
        dsc.setUrl(sysDataSource.getUrl());
        dsc.setUsername(sysDataSource.getUsername());
        dsc.setPassword(sysDataSource.getPassword());

        StrategyConfig strategyConfig = new StrategyConfig();
        TemplateConfig templateConfig = new TemplateConfig();
        ConfigBuilder config = new ConfigBuilder(new PackageConfig(), dsc, strategyConfig, templateConfig, gc);
        List<TableInfo> list = config.getTableInfoList();
        return Result.data(list);
    }


    /**
     * 代码生成
     * @param packageName　包名
     * @param prefix　前缀
     * @param modelName　模块名
     * @param datasourceId　数据源名
     * @param tableName　表名
     */
    @PreAuth
    @Log(value = "代码生成", exception = "代码生成请求异常")
    @ApiOperation(value = "代码生成", notes = "代码生成")
    @PostMapping("/generator-code")
    public void execute(@RequestParam String packageName, @RequestParam String prefix,
                        @RequestParam String modelName, @RequestParam String datasourceId,
                        @RequestParam String tableName, HttpServletRequest request,
                        HttpServletResponse response) {
        SysDataSource sysDataSource = sysDataSourceService.getById(datasourceId);
        String outputDir = System.getProperty("user.dir") + File.separator + "temp" + File.separator + "generator" + File.separator + UUID.randomUUID().toString();
        CodeConfig config = new CodeConfig();
        config.setDbType(DbType.getDbType(sysDataSource.getName()));
        config.setUrl(sysDataSource.getUrl());
        config.setUsername(sysDataSource.getUsername());
        config.setPassword(sysDataSource.getPassword());
        config.setDriverName(sysDataSource.getDriverClass());
        config.setModelName(modelName);
        config.setOutputDir(outputDir);
        config.setPackageName(packageName);
        config.setTableName(tableName);
        config.setPrefix(prefix);
        config.setOutputDir(outputDir);
        GeneratorUtil.execute(config);
        String fileName = "code" + UUID.randomUUID().toString() + ".zip";
        String filePath = outputDir + File.separator + fileName;
        // 压缩目录
        String[] srcDir = {outputDir + File.separator + (config.getPackageName().substring(0, config.getPackageName().indexOf(".")))};
        try {
            ZipUtil.toZip(srcDir, filePath, true);
            FileUtil.download(filePath, fileName, true, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
