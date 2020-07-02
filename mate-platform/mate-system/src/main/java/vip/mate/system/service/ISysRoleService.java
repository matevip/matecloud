package vip.mate.system.service;

import vip.mate.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRoleVO> tree();

}
