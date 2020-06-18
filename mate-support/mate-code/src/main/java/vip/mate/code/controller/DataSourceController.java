package vip.mate.code.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.code.entity.DataSource;
import vip.mate.code.service.IDataSourceService;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;

import java.util.List;


/**
 * <p>
 * 数据源表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-04-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/data-source")
@Api(value = "数据源", tags = {"数据源模块"})
public class DataSourceController extends BaseController {

    private final IDataSourceService dataSourceService;

    @GetMapping("list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "列表", notes = "查询列表")
    public Result<List<DataSource>> list (DataSource dataSource) {
        List<DataSource> list = dataSourceService.list();
        return Result.data(list);
    }




}

