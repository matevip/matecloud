package vip.mate.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import vip.mate.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    /**
     * 忽略租户信息
     * @param sysUser
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    SysUser selectOneIgnoreTenant(SysUser sysUser);
}
