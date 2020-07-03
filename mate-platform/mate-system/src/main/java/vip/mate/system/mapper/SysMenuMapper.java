package vip.mate.system.mapper;

import vip.mate.system.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> routes(String roleId);

}
