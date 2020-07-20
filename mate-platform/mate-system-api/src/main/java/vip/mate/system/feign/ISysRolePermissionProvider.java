package vip.mate.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vip.mate.core.feign.constant.FeignConstant;

import java.util.List;

@FeignClient(value = FeignConstant.MATE_CLOUD_SYSTEM)
public interface ISysRolePermissionProvider {

    /**
     * 通过roleId获取权限列表
     * @param roleId　角色ID
     * @return List
     */
    @GetMapping("/provider/sys-role-permission/get-permission")
    List<String> getMenuIdByRoleId(@RequestParam("roleId") String roleId);
}
