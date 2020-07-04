package vip.mate.system.controller;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.SystemConstant;
import vip.mate.core.common.util.CryptoUtil;
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

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysUserService.listPage(query));
    }

    @PostMapping("/saveOrUpdate")
    //@CacheUpdate(name=SystemConstant.SYS_USER_CACHE, key="#sysUser.account", value="#sysUser")
    public Result<?> saveOrUpdate(@Valid @RequestBody SysUser sysUser) {
        String password = sysUser.getPassword();
        if (StringUtils.isNotBlank(password) && sysUser.getId() == null) {
            password = passwordEncoder.encode(CryptoUtil.encodeMD5(password));
            sysUser.setPassword(password);
        }
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
    //@CacheInvalidate(name=SystemConstant.SYS_USER_CACHE, key="#{*}")
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

