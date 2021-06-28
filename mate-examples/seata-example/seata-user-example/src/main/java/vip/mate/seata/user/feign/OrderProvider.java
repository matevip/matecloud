package vip.mate.seata.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用订单接口
 *
 * @author pangu
 */
@FeignClient("seata-order-example")
public interface OrderProvider {
	/**
	 * 创建订单
	 */
	@PostMapping("/order")
	public void createOrder();
}
