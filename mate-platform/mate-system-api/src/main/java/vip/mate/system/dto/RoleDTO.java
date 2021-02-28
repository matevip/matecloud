package vip.mate.system.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {

	private Integer roleId;
	private String roleName;
	private String roleCode;
	private String roleDesc;
	private String delFlag;
	private int dsType;
	List<Integer> permissionIds;
	List<Integer> roleDepts;
}
