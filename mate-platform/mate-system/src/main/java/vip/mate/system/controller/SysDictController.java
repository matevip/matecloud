package vip.mate.system.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.service.ISysDictService;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-07-11
 */
@RestController
@RequestMapping("/sys-dict")
@AllArgsConstructor
@Api(tags = "系统字典资源管理")
public class SysDictController extends BaseController {

    private final ISysDictService sysDictService;

    @EnableToken
    @Log(value = "根据code查询字典列表", exception = "根据code查询字典列表请求异常")
    @GetMapping("/list-code")
    @ApiOperation(value = "根据code查询字典列表", notes = "根据code查询字典列表")
    public Result<?> listCode (String code) {
       return sysDictService.getList(code);
    }

    @EnableToken
    @Log(value = "根据code查询字典列表", exception = "根据code查询字典列表请求异常")
    @GetMapping("/get-dict-value")
    @ApiOperation(value = "根据code查询字典列表", notes = "根据code查询字典列表")
    public Result<?> getDictValue (String code, String dictKey) {
        return sysDictService.getValue(code, dictKey);
    }


    @EnableToken
    @Log(value = "字典分页列表", exception = "字典分页列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "获取字典分页列表", notes = "获取字典分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> listPage(Page page, Search search) {
        return Result.data(sysDictService.listPage(page, search));
    }

}

