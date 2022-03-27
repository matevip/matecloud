package vip.mate.code.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableInfoDTO implements Serializable {

    private static final long serialVersionUID = 8758316938862284050L;

    /**
     * 数据源名
     */
    private String dsName;

    /**
     * 表名
     */
    private String tableName;
}
