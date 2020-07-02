package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import vip.mate.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
public interface ISysUserService extends IService<SysUser> {

    boolean status(String ids, String status);

    IPage<SysUser> listPage(Map<String, String> query);

}
