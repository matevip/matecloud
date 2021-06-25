package vip.mate.seata.order.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.seata.order.entity.Order;
import vip.mate.seata.order.mapper.OrderMapper;
import vip.mate.seata.order.service.IOrderService;

/**
 * 订单业务实现类
 *
 * @author pangu
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
}
