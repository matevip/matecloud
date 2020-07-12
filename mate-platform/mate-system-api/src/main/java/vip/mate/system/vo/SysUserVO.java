package vip.mate.system.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserVO implements Serializable {

    private static final long serialVersionUID = 7284045793118911411L;

    @Excel(name = "用户编号" ,orderNum = "0", height = 20, width = 30, isImportField = "true_st")
    private Long id;
    @Excel(name = "账号" ,orderNum = "1", height = 20, width = 30, isImportField = "true_st")
    private String account;
    @Excel(name = "昵称" ,orderNum = "2", height = 20, width = 30, isImportField = "true_st")
    private String name;
    private Long departId;
    @Excel(name = "电话" ,orderNum = "5", height = 20, width = 30, isImportField = "true_st")
    private String telephone;
    private int status;

    @Excel(name = "部门" ,orderNum = "4", height = 20, width = 30, isImportField = "true_st")
    private String departName;
    @Excel(name = "状态" ,orderNum = "6", height = 20, width = 30, isImportField = "true_st")
    private String statusName;
    @Excel(name = "角色" ,orderNum = "3", height = 20, width = 30, isImportField = "true_st")
    private String roleName;

}
