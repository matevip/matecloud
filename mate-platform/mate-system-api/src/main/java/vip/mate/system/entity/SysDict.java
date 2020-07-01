package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import vip.mate.core.common.entity.MateEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author xuzf
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_dict")
public class SysDict extends MateEntity {

    private static final long serialVersionUID=1L;

    /**
     * 父主键
     */
    private Long parentId;

    /**
     * 字典码
     */
    private String code;

    /**
     * 字典值
     */
    private String dictKey;

    /**
     * 字典名称
     */
    private String dictValue;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字典备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否已删除
     */
    private Integer isDeleted;


}
