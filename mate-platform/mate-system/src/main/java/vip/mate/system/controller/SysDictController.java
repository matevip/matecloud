package vip.mate.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.core.web.controller.BaseController;
import vip.mate.system.entity.SysDict;
import vip.mate.system.feign.ISysDictProvider;

import java.util.List;

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

    private final ISysDictProvider sysDictProvider;

    @GetMapping("/list-code")
    @ApiOperation(value = "根据code查询字典列表", notes = "根据code查询字典列表")
    public Result<?> listCode (String code) {
       return sysDictProvider.getList(code);
    }

    @GetMapping("/get-dict-value")
    @ApiOperation(value = "根据code查询字典列表", notes = "根据code查询字典列表")
    public Result<?> getDictValue (String code, String dictKey) {
        return sysDictProvider.getValue(code, dictKey);
    }

}

