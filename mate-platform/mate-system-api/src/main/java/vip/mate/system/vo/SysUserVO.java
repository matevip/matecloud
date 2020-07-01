package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVO {

    private Long id;
    private String account;
    private String name;
    private Long departId;
    private String telephone;
    private int status;

    private String departName;
    private String statusName;

}
