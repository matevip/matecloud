package vip.mate.system.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import vip.mate.core.common.constant.MateConstant;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysRolePOI implements Serializable {

    private static final long serialVersionUID = 5758487990585198585L;

    @Excel(name = "角色编号" ,orderNum = "0", width = 30, isImportField = "true_st")
    private Long id;

    @Excel(name = "角色名称" ,orderNum = "1", width = 30, isImportField = "true_st")
    private String roleName;

    @Excel(name = "角色编码" ,orderNum = "2", width = 30, isImportField = "true_st")
    private String roleCode;

    @Excel(name = "角色描述" ,orderNum = "3", width = 30, isImportField = "true_st")
    private String description;

    @Excel(name = "创建时间", format = MateConstant.DATETIME_FORMAT, orderNum = "4", width = 30, isImportField = "true_st")
    private LocalDateTime createTime;
}
