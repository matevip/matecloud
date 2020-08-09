package vip.mate.component.controller;


import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.component.service.ISysConfigService;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.oss.props.OssProperties;
import vip.mate.core.web.controller.BaseController;

import javax.validation.Valid;

/**
 * <p>
 * 配置表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-08-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-config")
public class SysConfigController extends BaseController {

    private final ISysConfigService sysConfigService;

    @EnableToken
    @Log(value = "查询OSS配置", exception = "查询OSS配置异常")
    @GetMapping("/get-config-by-code")
    @ApiOperation(value = "查询OSS配置", notes = "查询OSS配置")
    public Result<?> getConfigByCode(@RequestParam String code) {
        return Result.data(sysConfigService.getConfigByCode(code));
    }

    @EnableToken
    @Log(value = "默认配置", exception = "默认配置异常")
    @ApiOperation(value = "默认配置", notes = "默认配置")
    @GetMapping("/default-oss")
    public Result<?> defaultOss() {
        return Result.data(sysConfigService.defaultOss());
    }

    @EnableToken
    @Log(value = "保存默认配置", exception = "保存默认配置异常")
    @ApiOperation(value = "保存默认配置", notes = "保存默认配置")
    @PostMapping("/save-default-oss")
    public Result<?> saveDefaultOss(@RequestParam String code) {
        boolean flag = sysConfigService.saveDefaultOss(code);
        if (flag){
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @EnableToken
    @Log(value = "保存OSS配置", exception = "保存OSS配置异常")
    @ApiOperation(value = "保存OSS配置", notes = "保存OSS配置")
    @PostMapping("/save-config-oss")
    public Result<?> saveConfigOss(@Valid @RequestBody OssProperties ossProperties, @RequestParam String code) {
        sysConfigService.saveConfigOss(ossProperties, code);
        return Result.success("操作成功");
    }

}

