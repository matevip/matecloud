package vip.mate.core.web.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.api.Result;

/**
 * 拦截404异常　/error页面数据为固定json返回
 * @author pangu
 */
@RestController
public class NotFoundException implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    /**
     * 重新定义error页面返回内容
     * @return Result
     */
    @RequestMapping(ERROR_PATH)
    public Result<?> error(){
        return Result.fail(HttpStatus.NOT_FOUND.value(), "接口不存在");
    }
}
