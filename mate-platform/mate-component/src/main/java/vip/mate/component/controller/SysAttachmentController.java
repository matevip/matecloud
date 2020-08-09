package vip.mate.component.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import vip.mate.component.service.ISysAttachmentService;
import vip.mate.core.auth.annotation.EnableToken;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;

import java.util.Map;

/**
 * <p>
 * 附件表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-08-09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sys-attachment")
public class SysAttachmentController extends BaseController {

    private final ISysAttachmentService sysAttachmentService;

    @EnableToken
    @Log(value = "文件分页列表", exception = "文件分页列表请求异常")
    @GetMapping("/list")
    @ApiOperation(value = "文件分页列表", notes = "文件分页列表，根据query查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> list(Page page, Search search){
        return Result.data(sysAttachmentService.listPage(page, search));
    }

    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        boolean flag = sysAttachmentService.upload(file);
        if (flag) {
            return Result.success("操作成功");
        }
        return Result.success("操作失败");
    }

}

