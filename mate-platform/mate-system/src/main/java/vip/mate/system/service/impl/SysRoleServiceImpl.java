package vip.mate.system.service.impl;

import vip.mate.system.entity.SysRole;
import vip.mate.system.mapper.SysRoleMapper;
import vip.mate.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRoleVO> tree() {
        return this.baseMapper.tree();
    }
}
