package vip.mate.system.service.impl;

import vip.mate.system.entity.Client;
import vip.mate.system.mapper.ClientMapper;
import vip.mate.system.service.ClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-07-09
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientMapper, Client> implements ClientService {

}
