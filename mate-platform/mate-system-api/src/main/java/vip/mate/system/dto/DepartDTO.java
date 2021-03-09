package vip.mate.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.system.entity.SysDepart;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepartDTO extends SysDepart {

	private List<DepartDTO> children;
}
