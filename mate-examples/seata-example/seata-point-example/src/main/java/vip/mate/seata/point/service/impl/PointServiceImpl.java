package vip.mate.seata.point.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.seata.point.entity.Point;
import vip.mate.seata.point.mapper.PointMapper;
import vip.mate.seata.point.service.IPointService;

/**
 * 积分业务实现类
 *
 * @author pangu
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements IPointService {
}
