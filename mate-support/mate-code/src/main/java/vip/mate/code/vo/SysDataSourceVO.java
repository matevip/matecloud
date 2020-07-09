package vip.mate.code.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDataSourceVO {

    private Long id;
    private String name;
}
