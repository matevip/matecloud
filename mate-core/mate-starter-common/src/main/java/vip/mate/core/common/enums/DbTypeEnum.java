package vip.mate.core.common.enums;

import lombok.Getter;

/**
 * 不同数据库类型的枚举
 * <p>
 * 用于标识mapping.xml中不同数据库的标识
 *
 * @author aaronuu
 */
@Getter
public enum DbTypeEnum {

    /**
     * mysql
     */
    MYSQL("mysql", "mysql"),

    /**
     * pgsql
     */
    PG_SQL("pgsql", "postgresql"),

    /**
     * oracle
     */
    ORACLE("oracle", "oracle"),

    /**
     * mssql
     */
    MS_SQL("mssql", "sqlserver");

    private final String code;

    private final String name;

    DbTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
