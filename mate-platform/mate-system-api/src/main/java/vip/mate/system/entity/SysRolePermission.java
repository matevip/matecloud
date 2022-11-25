package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色权限表
 * </p>
 *
 * @author xuzf
 * @since 2020-07-02
 */
@Data
@Accessors(chain = true)
@TableName("mate_sys_role_permission")
@Schema(name = "SysRolePermission对象", description = "角色权限表")
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单id
     */
    @Schema(description = "菜单id")
    private Long menuId;
    /**
     * 角色id
     */
    @Schema(description = "角色id")
    private Long roleId;


}
