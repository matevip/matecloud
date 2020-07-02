package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.common.entity.MateEntity;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author xuzf
 * @since 2020-07-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_role_permission")
public class SysRolePermission extends MateEntity {

    private static final long serialVersionUID=1L;

    /**
     * 菜单id
     */
    private Long menuId;

    /**
     * 角色id
     */
    private Long roleId;


}
