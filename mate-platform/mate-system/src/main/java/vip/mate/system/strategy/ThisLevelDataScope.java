package vip.mate.system.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import vip.mate.core.auth.util.MateAuthUser;
import vip.mate.core.common.exception.BaseException;
import vip.mate.core.database.enums.DataScopeTypeEnum;
import vip.mate.system.dto.RoleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 本级数据权限
 *
 * @author pangu
 */
@Component("2")
@AllArgsConstructor
public class ThisLevelDataScope implements AbstractDataScopeHandler {

	@Override
	public List<Long> getDeptIds(RoleDTO roleDto, DataScopeTypeEnum dataScopeTypeEnum) {
		// 用于存储部门id
		List<Long> deptIds = new ArrayList<>();
		String deptId = MateAuthUser.getUser().getDeptId();
		if (deptId == null) {
			throw new BaseException("部门信息为空！");
		}
		deptIds.add(Long.valueOf(MateAuthUser.getUser().getDeptId()));
		return deptIds;
	}
}
