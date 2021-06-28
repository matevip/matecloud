package vip.mate.seata.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 远程调用积分接口
 *
 * @author pangu
 */
@FeignClient("seata-point-example")
public interface PointProvider {
	/**
	 * 创建积分
	 */
	@GetMapping("/point")
	public void createPoint();
}
