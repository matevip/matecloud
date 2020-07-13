package vip.mate.system.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserPOI implements Serializable {

    private static final long serialVersionUID = -5298570444065758538L;

    @Excel(name = "用户编号", orderNum = "0", height = 20, width = 30, isImportField = "true_st")
    private Long id;

    @Excel(name = "账号", orderNum = "1", height = 20, width = 30, isImportField = "true_st")
    private String account;

    @Excel(name = "昵称", orderNum = "2", height = 20, width = 30, isImportField = "true_st")
    private String name;

    @Excel(name = "角色", orderNum = "3", height = 20, width = 30, isImportField = "true_st")
    private String roleName;

    @Excel(name = "部门", orderNum = "4", height = 20, width = 30, isImportField = "true_st")
    private String departName;

    @Excel(name = "电话", orderNum = "5", height = 20, width = 30, isImportField = "true_st")
    private String telephone;

    @Excel(name = "状态", orderNum = "6", height = 20, width = 30, isImportField = "true_st")
    private String statusName;
}

