package vip.mate.code.dto;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

@Data
public class CodeConfig {

    private String packageName;
    private String prefix;
    private String modelName;
    private DbType dbType;
    private String url;
    private String username;
    private String password;
    private String driverName;
    private String outputDir;
    private String tableName;

}
