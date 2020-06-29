package vip.mate.core.web.tree;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Mapper数据模型节点
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MapperNode extends TreeNode {

    private String title;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long key;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long value;
}
