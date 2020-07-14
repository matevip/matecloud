package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.entity.SysClient;
import vip.mate.system.poi.SysClientPOI;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-07-14
 */
public interface ISysClientService extends IService<SysClient> {

    IPage<SysClient> listPage(Map<String, String> query);

    boolean status(String ids, String status);

    List<SysClientPOI> export();
}
