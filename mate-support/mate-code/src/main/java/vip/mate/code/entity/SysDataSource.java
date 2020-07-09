package vip.mate.code.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import vip.mate.core.database.entity.BaseEntity;

import java.time.LocalDateTime;

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
    private String name;

    /**
     * 驱动类
     */
    private String driverClass;

    /**
     * 连接地址
     */
    private String url;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Boolean status;


    /**
     * 逻辑删除
     */
    private Boolean isDeleted;


}
