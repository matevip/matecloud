package vip.mate.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysClient;
import vip.mate.system.entity.SysDict;
import vip.mate.system.service.ISysDictService;

import javax.validation.Valid;
import java.util.Collection;

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

    /**
     * 根据code查询字典项列表
     * @param code 字典码
     * @return Result<List<SysDict>>
     */
    @EnableToken
    @Log(value = "根据code查询字典项列表", exception = "根据code查询字典项列表异常")
    @GetMapping("list-value")
    @ApiOperation(value = "根据code查询字典项列表", notes = "根据code查询字典项列表")
    public Result<?> listValue (@RequestParam String code) {
        return Result.data(sysDictService.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getCode, code)
                .ne(SysDict::getParentId, 0)
                .orderByAsc(SysDict::getSort)));
    }

    @EnableToken
    @Log(value = "新增或修改字典", exception = "新增或修改字典请求异常")
    @PostMapping("/save-or-update")
    @ApiOperation(value = "添加系统字典", notes = "添加系统字典,支持新增或修改")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysDict sysDict) {
        if (sysDictService.saveOrUpdate(sysDict)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "获取字典信息", exception = "获取字典信息请求异常")
    @GetMapping("/info")
    @ApiOperation(value = "获取字典信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
    })
    public Result<?> getSysUser(SysDict sysDict) {
        return Result.data(sysDictService.getById(sysDict.getId()));
    }

    @EnableToken
    @Log(value = "批量删除字典", exception = "批量删除字典请求异常")
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除字典", notes = "批量删除字典")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    @Transactional
    public Result<?> delete(@RequestParam String ids) {
        Collection idsCollection = CollectionUtil.stringToCollection(ids);
        if (sysDictService.removeByIds(idsCollection)){
            //批量删除字典列表的同时，也要删除字典项的内容
            for (Object obj : idsCollection) {
               sysDictService.remove(new LambdaQueryWrapper<SysDict>().eq(SysDict::getParentId, obj));
            }
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

