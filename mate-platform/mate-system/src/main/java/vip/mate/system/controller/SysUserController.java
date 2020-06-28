package vip.mate.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysUserService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-user")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    @GetMapping("/list")
    public Result<?> list(@RequestParam Map<String, String> query) {
        long current = CollectionUtil.strToLong(query.get("current"), 0L);
        long size = CollectionUtil.strToLong(query.get("size"), 0L);
        IPage<SysUser> page = new Page<>(current, size);
        return Result.data(sysUserService.page(page));
    }

    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysUser sysUser) {
        if (sysUserService.saveOrUpdate(sysUser)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @GetMapping("/info")
    public Result<?> getSysUser(SysUser sysUser) {
        LambdaQueryWrapper<SysUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SysUser::getId, sysUser.getId());
        return Result.data(sysUserService.getOne(lambdaQueryWrapper));
    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestParam String ids) {
        if (sysUserService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/status")
    public Result<?> status(@RequestParam String ids, @RequestParam String status) {
        if (sysUserService.status(ids, status)) {
            return Result.success("批量修改成功");
        }
        return Result.fail("操作失败");
    }

}

