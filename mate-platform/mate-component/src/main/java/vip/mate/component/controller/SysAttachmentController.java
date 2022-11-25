package vip.mate.component.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.component.service.ISysAttachmentService;
import vip.mate.core.auth.annotation.PreAuth;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.log.annotation.Log;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;

import java.util.Collection;
import java.util.Iterator;

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
@RequestMapping("/attachment")
@Tag(name = "附件管理")
public class SysAttachmentController extends BaseController {

    private final ISysAttachmentService sysAttachmentService;

    /**
     * 附件分页
     * @param search　关键词
     * @return Result
     */
    @PreAuth
    @Log(value = "附件分页")
    @GetMapping("/page")
    @Operation(summary = "附件分页")
    @Parameters({
            @Parameter(name = "current", required = true,  description = "当前页", in = ParameterIn.DEFAULT),
            @Parameter(name = "size", required = true,  description = "每页显示数据", in = ParameterIn.DEFAULT),
            @Parameter(name = "keyword", required = true,  description = "模糊查询关键词", in = ParameterIn.DEFAULT),
            @Parameter(name = "startDate", required = true,  description = "创建开始日期", in = ParameterIn.DEFAULT),
            @Parameter(name = "endDate", required = true,  description = "创建结束日期", in = ParameterIn.DEFAULT),
    })
    public Result<?> page(Search search) {
        return Result.data(sysAttachmentService.listPage(search));
    }

    /**
     * 附件上传
     * @param file　文件
     * @return Result
     */
    @Log(value = "附件上传")
    @Operation(summary = "附件上传")
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) {
        return sysAttachmentService.upload(file);
    }

    /**
     * 删除文件
     * @param ids id多个采用逗号分隔
     * @return
     */
    @PreAuth
    @Log(value = "删除文件")
    @Operation(summary = "删除文件")
    @Parameters({
            @Parameter(name = "ids", required = true,  description = "多个用,号隔开", in = ParameterIn.DEFAULT)
    })
    @PostMapping("/del")
    public Result<?> del(@RequestParam String ids) {
        Collection collection = CollectionUtil.stringToCollection(ids);
        for (Iterator<Long> it = collection.iterator(); it.hasNext(); ) {
            long id = it.next();
            sysAttachmentService.delete(id);
        }
        return Result.success("删除成功");
    }
}

