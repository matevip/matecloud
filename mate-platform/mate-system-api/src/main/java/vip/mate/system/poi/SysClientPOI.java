package vip.mate.system.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import vip.mate.core.common.constant.MateConstant;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysClientPOI implements Serializable {

    private static final long serialVersionUID = -8021224499106732031L;

    @Excel(name = "客户端ID" ,orderNum = "0", width = 30, isImportField = "true_st")
    private Long id;

    @Excel(name = "客户端标识" ,orderNum = "1", width = 30, isImportField = "true_st")
    private String clientId;

    @Excel(name = "客户端密钥" ,orderNum = "2", width = 30, isImportField = "true_st")
    private String clientSecret;

    @Excel(name = "授权类型" ,orderNum = "3", width = 30, isImportField = "true_st")
    private String authorizedGrantTypes;

    @Excel(name = "授权范围" ,orderNum = "4", width = 30, isImportField = "true_st")
    private String scope;

    @Excel(name = "回调地址" ,orderNum = "5", width = 30, isImportField = "true_st")
    private String webServerRedirectUri;

    @Excel(name = "创建时间", format = MateConstant.DATETIME_FORMAT, orderNum = "6", width = 30, isImportField = "true_st")
    private LocalDateTime createTime;

}
