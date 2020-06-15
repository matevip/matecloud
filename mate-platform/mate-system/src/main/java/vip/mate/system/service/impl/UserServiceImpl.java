package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.system.entity.User;
import vip.mate.system.mapper.UserMapper;
import vip.mate.system.service.IUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xuzf
 * @since 2020-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
