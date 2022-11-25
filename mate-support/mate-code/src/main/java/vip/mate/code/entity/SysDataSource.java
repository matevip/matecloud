package vip.mate.code.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

/**
 * <p>
 * 数据源表
 * </p>
 *
 * @author xuzf
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_sys_data_source")
public class SysDataSource extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;
    /**
     * 数据库类型
     */
    @Schema(description = "数据库类型")
    private String dbType;
    /**
     * 驱动类型
     */
    @Schema(description = "驱动类型")
    private String driverClass;
    /**
     * 连接地址
     */
    @Schema(description = "连接地址")
    private String url;
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String username;
    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;


}
