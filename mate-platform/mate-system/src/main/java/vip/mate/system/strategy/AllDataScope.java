package vip.mate.system.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vip.mate.core.database.enums.DataScopeTypeEnum;
import vip.mate.system.dto.RoleDTO;
import vip.mate.system.entity.SysDepart;
import vip.mate.system.service.ISysDepartService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有数据范围
 *
 * @author pangu
 */
@Component("1")
@AllArgsConstructor
public class AllDataScope implements AbstractDataScopeHandler {

	private final ISysDepartService sysDepartService;

	@Override
	public List<Long> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
		List<SysDepart> sysDeparts = sysDepartService.list();
		return sysDeparts.stream().map(SysDepart::getId).collect(Collectors.toList());
	}


}
