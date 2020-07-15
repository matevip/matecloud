package vip.mate.system.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.system.entity.SysDict;
import vip.mate.system.service.ISysDictService;

import java.util.List;

/**
 * 字典feign或者Dubbo调用
 * @author pangu
 */
@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Feign或者Dubbo调用字典操作")
public class SysDictProvider implements ISysDictProvider {

    private final ISysDictService sysDictService;

    @Override
    @ApiOperation(value = "根据code和dictKey获取值", notes = "根据code和dictKey获取值")
    public Result<String> getValue(String code, String dictKey) {
        return sysDictService.getValue(code, dictKey);
    }

    @Override
    @ApiOperation(value = "根据code获取字典列表", notes = "根据code获取字典列表")
    public Result<List<SysDict>> getList(String code) {
        return sysDictService.getList(code);
    }
}
