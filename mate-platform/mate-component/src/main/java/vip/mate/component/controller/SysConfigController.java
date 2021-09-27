package vip.mate.component.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.mate.component.service.ISysConfigService;
import vip.mate.core.auth.annotation.PreAuth;
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
@RequestMapping("/config")
@Api(tags = "配置管理")
public class SysConfigController extends BaseController {

    private final ISysConfigService sysConfigService;

    /**
     * 查询OSS配置
     * @param code　代码
     * @return Result
     */
    @PreAuth
    @Log(value = "查询OSS配置")
    @GetMapping("/get-config-by-code")
    @ApiOperation(value = "查询OSS配置")
    public Result<?> getConfigByCode(@RequestParam String code) {
        return Result.data(sysConfigService.getConfigByCode(code));
    }

    /**
     * 默认配置
     * @return Result
     */
    @PreAuth
    @Log(value = "默认配置")
    @ApiOperation(value = "默认配置")
    @GetMapping("/default-oss")
    public Result<?> defaultOss() {
        return Result.data(sysConfigService.defaultOss());
    }

    /**
     * 保存默认配置
     * @param code code
     * @return Result
     */
    @PreAuth
    @Log(value = "保存默认配置")
    @ApiOperation(value = "保存默认配置")
    @PostMapping("/save-default-oss")
    public Result<?> saveDefaultOss(@RequestParam String code) {
        return Result.condition(sysConfigService.saveDefaultOss(code));
    }

    /**
     * 保存OSS配置
     * @param ossProperties　oss配置
     * @param code
     * @return
     */
    @PreAuth
    @Log(value = "保存OSS配置")
    @ApiOperation(value = "保存OSS配置")
    @PostMapping("/save-config-oss")
    public Result<?> saveConfigOss(@Valid @RequestBody OssProperties ossProperties, @RequestParam String code) {
        return Result.condition(sysConfigService.saveConfigOss(ossProperties, code));
    }

}

