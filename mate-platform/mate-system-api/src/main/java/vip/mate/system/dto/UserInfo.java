package vip.mate.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.mate.system.entity.SysUser;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel(value = "用户信息封装")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -7657663783681647907L;

    /**
     * 系统用户信息
     */
    @ApiModelProperty("系统用户信息")
    private SysUser sysUser;

    /**
     * 系统权限标识组
     */
    @ApiModelProperty("系统权限标识组")
    private List<String> permissions;

    /**
     * 系统角色标识组
     */
    @ApiModelProperty(value = "系统角色标识组")
    private List<Long> roleIds;


}
