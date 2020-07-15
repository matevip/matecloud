package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import vip.mate.system.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 系统日志表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-07-15
 */
public interface ISysLogService extends IService<SysLog> {

    IPage<SysLog> listPage(Map<String, String> query);
}
