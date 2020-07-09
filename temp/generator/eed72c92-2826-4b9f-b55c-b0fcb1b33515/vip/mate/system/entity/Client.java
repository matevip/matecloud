package vip.mate.system.entity;

import vip.mate.core.database.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户端表
 * </p>
 *
 * @author pangu
 * @since 2020-07-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mate_client")
@ApiModel(value="Client对象", description="客户端表")
public class Client extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "客户端id")
    private String clientId;

    @ApiModelProperty(value = "客户端密钥")
    private String clientSecret;

    @ApiModelProperty(value = "资源集合")
    private String resourceIds;

    @ApiModelProperty(value = "授权范围")
    private String scope;

    @ApiModelProperty(value = "授权类型")
    private String authorizedGrantTypes;

    @ApiModelProperty(value = "回调地址")
    private String webServerRedirectUri;

    @ApiModelProperty(value = "权限")
    private String authorities;

    @ApiModelProperty(value = "令牌过期秒数")
    private Integer accessTokenValidity;

    @ApiModelProperty(value = "刷新令牌过期秒数")
    private Integer refreshTokenValidity;

    @ApiModelProperty(value = "附加说明")
    private String additionalInformation;

    @ApiModelProperty(value = "自动授权")
    private String autoapprove;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建部门")
    private Long createDept;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "是否已删除")
    private Integer isDeleted;


}
