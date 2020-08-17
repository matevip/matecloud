package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID=1L;


    /**
     * 菜单标题
     */
    private String name;

    /**
     * 菜单权限
     */
    private String permission;

    /**
     * 路径
     */
    private String path;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 是否缓存该页面: 1:是  0:不是
     */
    private String keepAlive;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 状态：0：启用　1：禁用
     */
    private String status;

    /**
     * 删除标识
     */
    private String isDeleted;

    /**
     * 租户ID
     */
    private Long tenantId;


}
