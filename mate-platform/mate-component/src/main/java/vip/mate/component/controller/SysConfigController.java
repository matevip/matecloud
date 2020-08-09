package vip.mate.component.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import vip.mate.component.service.ISysConfigService;
import vip.mate.core.common.api.Result;
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

    @GetMapping("/get-config-by-code")
    public Result<?> getConfigByCode(@RequestParam String code) {
        return Result.data(sysConfigService.getConfigByCode(code));
    }

    @GetMapping("/default-oss")
    public Result<?> defaultOss() {
        return Result.data(sysConfigService.defaultOss());
    }

    @PostMapping("/save-default-oss")
    public Result<?> saveDefaultOss(@RequestParam String code) {
        boolean flag = sysConfigService.saveDefaultOss(code);
        if (flag){
            return Result.success("操作成功");
        }
        return Result.fail("操作失败");
    }

    @PostMapping("/save-config-oss")
    public Result<?> saveConfigOss(@Valid @RequestBody OssProperties ossProperties, @RequestParam String code) {
        sysConfigService.saveConfigOss(ossProperties, code);
        return Result.success("操作成功");
    }

}

