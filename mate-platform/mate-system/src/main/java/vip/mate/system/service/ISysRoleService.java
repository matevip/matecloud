package vip.mate.system.service;

import vip.mate.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.poi.SysRolePOI;
import vip.mate.system.vo.SysRoleVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 角色树
     *
     * @return
     */
    List<SysRoleVO> tree();

    /**
     * 查询角色列表
     *
     * @param search
     * @return
     */
    List<SysRole> listSearch(Map<String, String> search);

    /**
     * 查询权限集
     *
     * @param id
     * @return
     */
    List<String> getPermission(String id);

    /**
     * 角色导出
     *
     * @return
     */
    List<SysRolePOI> export();

}
