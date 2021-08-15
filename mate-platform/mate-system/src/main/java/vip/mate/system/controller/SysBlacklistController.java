package vip.mate.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.rule.entity.BlackList;
import vip.mate.core.rule.service.IRuleCacheService;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysBlacklist;
import vip.mate.system.service.ISysBlacklistService;

import javax.validation.Valid;
import java.util.Collection;

/**
 * <p>
 * 系统黑名单表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-08-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/blacklist")
@Api(tags = "黑名单管理")
public class SysBlacklistController extends BaseController {

    private final ISysBlacklistService sysBlacklistService;

    private final IRuleCacheService ruleCacheService;

    /**
     * 黑名单分页
     * @param search　关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "黑名单分页", exception = "黑名单分页请求异常")
    @GetMapping("/page")
    @ApiOperation(value = "黑名单分页", notes = "黑名单分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> page(Search search) {
        return Result.data(sysBlacklistService.listPage(search));
    }

    /**
     * 黑名单设置
     * @param sysBlacklist SysBlacklist对象
     * @return Result
     */
    @PreAuth
    @Log(value = "黑名单设置", exception = "黑名单设置请求异常")
    @PostMapping("/set")
    @ApiOperation(value = "黑名单设置", notes = "黑名单设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysBlacklist sysBlacklist) {
        BlackList blackList = new BlackList();
        //删除缓存
        if (sysBlacklist.getId() != null) {
            SysBlacklist b = sysBlacklistService.getById(sysBlacklist.getId());
            BeanUtils.copyProperties(b, blackList);
            ruleCacheService.deleteBlackList(blackList);
        }
        //删除缓存
        if (sysBlacklistService.saveOrUpdate(sysBlacklist)) {
            //缓存操作-----start
            SysBlacklist blacklistCurr = sysBlacklistService.getById(sysBlacklist.getId());
            BeanUtils.copyProperties(blacklistCurr, blackList);
            ruleCacheService.setBlackList(blackList);
            //缓存操作----end
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    /**
     * 黑名单信息
     * @param id　id
     * @return Result
     */
    @PreAuth
    @Log(value = "黑名单信息", exception = "黑名单信息请求异常")
    @GetMapping("/get")
    @ApiOperation(value = "黑名单信息", notes = "黑名单信息,根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
    })
    public Result<?> info(@RequestParam String id) {
        return Result.data(sysBlacklistService.getById(id));
    }

    /**
     * 黑名单删除
     * @param ids　多个id采用逗号分隔
     * @return Result
     */
    @PreAuth
    @Log(value = "黑名单删除", exception = "黑名单删除请求异常")
    @PostMapping("/del")
    @ApiOperation(value = "黑名单删除", notes = "黑名单删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    @Transactional(rollbackFor = Exception.class)
    public Result<?> del(@RequestParam String ids) {
        Collection collection = CollectionUtil.stringToCollection(ids);
        BlackList blackList = new BlackList();
        //处理缓存----start
        for (Object id : collection) {
            SysBlacklist blacklistCurr = sysBlacklistService.getById(CollectionUtil.objectToLong(id, 0L));
            BeanUtils.copyProperties(blacklistCurr, blackList);
            ruleCacheService.deleteBlackList(blackList);
        }
        //处理缓存----end
        if (sysBlacklistService.removeByIds(collection)) {
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 黑名单状态
     * @param ids　多个id采用逗号分隔
     * @param status　状态：启用、禁用
     * @return Result
     */
    @PreAuth
    @Log(value = "黑名单状态", exception = "黑名单状态请求异常")
    @PostMapping("/set-status")
    @ApiOperation(value = "黑名单状态", notes = "黑名单状态,状态包括：启用、禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
            @ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
    })
    public Result<?> setStatus(@RequestParam String ids, @RequestParam String status) {
        if (sysBlacklistService.status(ids, status)) {
            return Result.success("批量修改成功");
        }
        return Result.fail("操作失败");
    }
}

