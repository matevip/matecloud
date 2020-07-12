package vip.mate.system.controller;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.common.constant.SystemConstant;
import vip.mate.core.common.util.CryptoUtil;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.core.web.util.ExcelUtil;
import vip.mate.system.entity.SysUser;
import vip.mate.system.service.ISysUserService;
import vip.mate.system.vo.SysUserVO;

import javax.servlet.http.HttpServletResponse;
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
@Api(tags = "系统用户资源管理")
public class SysUserController extends BaseController {

    private final ISysUserService sysUserService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    @ApiOperation(value = "获取分页接口列表", notes = "获取分页接口列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(@RequestParam Map<String, String> query) {
        return Result.data(sysUserService.listPage(query));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "添加系统用户", notes = "添加系统用户,支持新增或修改")
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
    @ApiOperation(value = "获取用户信息", notes = "根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
    })
    public Result<?> getSysUser(SysUser sysUser) {
        return Result.data(sysUserService.getById(sysUser.getId()));
    }

    @PostMapping("/delete")
    //@CacheInvalidate(name=SystemConstant.SYS_USER_CACHE, key="#{*}")
    @ApiOperation(value = "批量删除用户数据", notes = "批量删除用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    public Result<?> delete(@RequestParam String ids) {
        if (sysUserService.removeByIds(CollectionUtil.stringToCollection(ids))){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/status")
    @ApiOperation(value = "批量设置用户状态", notes = "状态包括：启用、禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form"),
            @ApiImplicitParam(name = "status", required = true, value = "状态", paramType = "form")
    })
    public Result<?> status(@RequestParam String ids, @RequestParam String status) {
        if (sysUserService.status(ids, status)) {
            return Result.success("批量修改成功");
        }
        return Result.fail("操作失败");
    }

    @PostMapping("/savePwd")
    @ApiOperation(value = "设置用户密码", notes = "设置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "用户ID", paramType = "form"),
            @ApiImplicitParam(name = "password", required = true, value = "密码", paramType = "form")
    })
    public Result<?> savePwd(@RequestParam String id, @RequestParam String password) {
        String pwd = null;
        if (StringUtils.isNotBlank(password)) {
            pwd = passwordEncoder.encode(CryptoUtil.encodeMD5(password));
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(CollectionUtil.strToLong(id, 0L));
        sysUser.setPassword(pwd);
        if (sysUserService.updateById(sysUser)) {
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @GetMapping("/exportUser")
    @ApiOperation(value = "导出用户列表", notes = "导出用户列表")
    public void export(HttpServletResponse response) {
        List<SysUserVO> sysUserVOS = sysUserService.export();
        //使用工具类导出excel
        ExcelUtil.exportExcel(sysUserVOS, null, "用户", SysUserVO.class, "user", response);
    }

}

