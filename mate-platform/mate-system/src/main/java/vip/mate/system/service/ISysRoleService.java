package vip.mate.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.database.entity.Search;
import vip.mate.system.entity.SysRole;
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

    /**
     * 分页列表
     *
     * @param search
     * @return
     */
    IPage<SysRole> listPage(Search search);

    /**
     * 设置角色
     *
     * @param sysRole 角色对象
     * @return 布尔
     */
    boolean set(SysRole sysRole);

    /**
     * 根据ids批量删除角色
     *
     * @param ids ID列表
     * @return boolean
     */
    boolean batchDeleteByIds(String ids);
}
