package vip.mate.system.poi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import vip.mate.core.common.constant.MateConstant;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysMenuPOI implements Serializable {

    private static final long serialVersionUID = 7842281865580909091L;

    @Excel(name = "菜单编号" ,orderNum = "0", width = 30, isImportField = "true_st")
    private Long id;

    @Excel(name = "菜单名称" ,orderNum = "1", width = 30, isImportField = "true_st")
    private String name;

    @Excel(name = "菜单权限" ,orderNum = "2", width = 30, isImportField = "true_st")
    private String permission;

    @Excel(name = "菜单路径" ,orderNum = "3", width = 30, isImportField = "true_st")
    private String path;

    @Excel(name = "菜单类型" ,orderNum = "4", replace = { "目录_0", "菜单_1", "按钮_2" }, width = 30, isImportField = "true_st")
    private String type;

    @Excel(name = "创建时间", format = MateConstant.DATETIME_FORMAT, orderNum = "5", width = 30, isImportField = "true_st")
    private LocalDateTime createTime;
}
