package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 7284045793118911411L;

    private Long id;
    private String account;
    private String name;
    private Long departId;
    private String telephone;
    private int status;

    private String departName;
    private String statusName;
    private String roleName;

}
