package vip.mate.component.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;
import vip.mate.component.entity.SysAttachment;
import vip.mate.core.database.entity.Search;
import vip.mate.system.entity.SysLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 附件表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-08-09
 */
public interface ISysAttachmentService extends IService<SysAttachment> {

    IPage<SysAttachment> listPage(Page page, Search search);

    boolean upload(MultipartFile file);

    boolean delete(Long id);

}
