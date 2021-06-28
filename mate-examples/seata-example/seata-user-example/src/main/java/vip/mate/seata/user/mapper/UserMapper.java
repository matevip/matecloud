package vip.mate.seata.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.mate.seata.user.entity.User;

/**
 * 用户mapper类
 *
 * @author pangu
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
