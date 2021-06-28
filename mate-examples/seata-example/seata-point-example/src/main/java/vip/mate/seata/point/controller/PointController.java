package vip.mate.seata.point.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.seata.point.entity.Point;
import vip.mate.seata.point.service.IPointService;

@RestController
@RequiredArgsConstructor
public class PointController {

	private final IPointService pointService;

	/**
	 * 创建积分
	 */
	@Transactional(rollbackFor = Exception.class)
	@PostMapping("/point")
	public void setPoint() {
		Point point = new Point();
		point.setCount(10);
		pointService.saveOrUpdate(point);
	}
}
