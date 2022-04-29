package vip.mate.code.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class TableInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 存储引擎
     */
    private String engine;

    /**
     * 表备注
     */
    private String tableComment;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
