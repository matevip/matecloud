package vip.mate.seata.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.seata.order.entity.Order;
import vip.mate.seata.order.service.IOrderService;

/**
 * 订单控制器类
 *
 * @author pangu
 */
@RestController
@RequiredArgsConstructor
public class OrderController {
	private final IOrderService orderService;

	@Transactional(rollbackFor = Exception.class)
	@PostMapping("/order")
	public void createOrder() {
		Order order = new Order();
		int a = 1 / 0;
		order.setMoney(100);
		orderService.save(order);
	}
}
