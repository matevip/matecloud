package vip.mate.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
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
@RequestMapping("/dict")
@AllArgsConstructor
@Tag(name = "字典管理")
public class SysDictController extends BaseController {

    private final ISysDictService sysDictService;

    /**
     * 通过code查询所有字典列表
     *
     * @param code 　code
     * @return Result
     */
    @PreAuth
    @Log(value = "字典列表code查询", exception = "字典列表请求异常")
    @GetMapping("/list-code")
    @Operation(summary = "字典列表code查询", description = "字典列表code查询")
    public Result<?> listCode(String code) {
        return sysDictService.getList(code);
    }

    /**
     * 根据code和key获取字典value
     *
     * @param code    code
     * @param dictKey key
     * @return Result
     */
    @PreAuth
    @Log(value = "字典列表key查询", exception = "字典列表key查询请求异常")
    @GetMapping("/get-dict-value")
    @Operation(summary = "字典列表key查询", description = "字典列表key查询")
    public Result<?> getDictValue(String code, String dictKey) {
        return sysDictService.getValue(code, dictKey);
    }


    /**
     * 字典分页
     *
     * @param page   分页参数
     * @param search 　搜索关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "字典分页", exception = "字典分页请求异常")
    @GetMapping("/page")
    @Operation(summary = "字典分页", description = "字典分页")
    @Parameters({
            @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
            @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> page(Page page, Search search) {
        return Result.data(sysDictService.listPage(page, search));
    }

    /**
     * 根据code查询字典项列表
     *
     * @param code 字典码
     * @return Result<List < SysDict>>
     */
    @PreAuth
    @Log(value = "字典项列表", exception = "字典项列表异常")
    @GetMapping("list-value")
    @Operation(summary = "字典项列表", description = "字典项列表")
    public Result<?> listValue(@RequestParam String code) {
        return Result.data(sysDictService.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getCode, code)
                .ne(SysDict::getParentId, 0)
                .orderByAsc(SysDict::getSort)));
    }

    /**
     * 字典设置支持新增和修改
     *
     * @param sysDict SysDict对象
     * @return Result
     */
    @PreAuth
    @Log(value = "字典设置", exception = "字典设置请求异常")
    @PostMapping("/set")
    @Operation(summary = "字典设置", description = "字典设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysDict sysDict) {
        return Result.condition(sysDictService.saveOrUpdate(sysDict));
    }

    /**
     * 字典信息
     *
     * @param id id
     * @return Result
     */
    @PreAuth
    @Log(value = "字典信息", exception = "字典信息请求异常")
    @GetMapping("/get")
    @Operation(summary = "字典信息", description = "根据ID查询")
    @Parameters({
            @Parameter(name = "id", required = true,  description = "主键ID", in = ParameterIn.DEFAULT),
    })
    public Result<?> get(@RequestParam String id) {
        return Result.data(sysDictService.getById(id));
    }

    /**
     * 字典删除
     *
     * @param ids 多个id采用逗号分隔
     * @return Result
     */
    @PreAuth
    @Log(value = "字典删除", exception = "字典删除请求异常")
    @PostMapping("/del")
    @Operation(summary = "字典删除", description = "字典删除")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    @Transactional(rollbackFor = Exception.class)
    public Result<?> del(@RequestParam String ids) {
        Collection idsCollection = CollectionUtil.stringToCollection(ids);
        if (sysDictService.removeByIds(idsCollection)) {
            //批量删除字典列表的同时，也要删除字典项的内容
            for (Object obj : idsCollection) {
                sysDictService.remove(new LambdaQueryWrapper<SysDict>().eq(SysDict::getParentId, obj));
            }
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }
}

