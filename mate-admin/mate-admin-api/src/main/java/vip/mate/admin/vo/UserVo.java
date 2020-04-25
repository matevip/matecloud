package vip.mate.admin.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.admin.entity.User;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ApiScopeVO对象", description = "ApiScopeVO对象")
public class UserVo extends User {

    private static final long serialVersionUID = 1L;

    /**
     * role
     */
    private String role;
}
