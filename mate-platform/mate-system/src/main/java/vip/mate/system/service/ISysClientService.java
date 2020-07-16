package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.database.entity.Search;
import vip.mate.system.entity.SysClient;
import vip.mate.system.poi.SysClientPOI;

import java.util.List;

/**
 * <p>
 * 客户端表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-07-14
 */
public interface ISysClientService extends IService<SysClient> {

    IPage<SysClient> listPage(Page page, Search search);

    boolean status(String ids, String status);

    List<SysClientPOI> export();
}
