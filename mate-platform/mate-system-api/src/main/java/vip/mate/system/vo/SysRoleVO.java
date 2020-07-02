package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleVO implements Serializable {

    private static final long serialVersionUID = -9037938910709841835L;

    private Long id;
    private String name;
}
