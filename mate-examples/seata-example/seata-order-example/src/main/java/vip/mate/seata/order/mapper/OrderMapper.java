package vip.mate.seata.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.Order;

/**
 * 订单mapper
 *
 * @author pangu
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
