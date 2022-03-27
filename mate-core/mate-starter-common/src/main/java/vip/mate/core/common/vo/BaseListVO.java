package vip.mate.core.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础响应视图对象
 *
 * @author pangu
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseListVO implements Serializable {
    private Long key;
    private String title;
}
