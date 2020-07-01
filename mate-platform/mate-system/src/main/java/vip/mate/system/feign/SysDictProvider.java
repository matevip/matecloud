package vip.mate.system.feign;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;
import vip.mate.system.entity.SysDict;
import vip.mate.system.service.ISysDictService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class SysDictProvider implements ISysDictProvider {

    private final ISysDictService sysDictService;

    @Override
    public Result<String> getValue(String code, String dictKey) {
        return sysDictService.getValue(code, dictKey);
    }

    @Override
    public Result<List<SysDict>> getList(String code) {
        return sysDictService.getList(code);
    }
}
