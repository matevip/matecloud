package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysClient;
import vip.mate.system.mapper.SysClientMapper;
import vip.mate.system.poi.SysClientPOI;
import vip.mate.system.service.ISysClientService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户端表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-07-14
 */
@Service
public class SysClientServiceImpl extends ServiceImpl<SysClientMapper, SysClient> implements ISysClientService {

    @Override
    public IPage<SysClient> listPage(Map<String, String> query) {
        long current = CollectionUtil.strToLong(query.get("current"), 0L);
        long size = CollectionUtil.strToLong(query.get("size"), 0L);
        IPage<SysClient> page = new Page<>(current, size);

        String keyword = String.valueOf(query.get("keyword"));
        String startDate = String.valueOf(query.get("startDate"));
        String endDate = String.valueOf(query.get("endDate"));
        LambdaQueryWrapper<SysClient> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(startDate) && !startDate.equals("null")) {
            lambdaQueryWrapper.between(SysClient::getCreateTime, startDate, endDate);
        }
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysClient::getClientId, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysClient::getId, keyword);
        }
        return this.baseMapper.selectPage(page, lambdaQueryWrapper);
    }

    @Override
    public boolean status(String ids, String status) {
        Collection<? extends Serializable> collection = CollectionUtil.stringToCollection(ids);

        for (Object id: collection) {
            SysClient sysClient = this.baseMapper.selectById(CollectionUtil.objectToLong(id, 0L));
            sysClient.setStatus(status);
            this.baseMapper.updateById(sysClient);
        }
        return true;
    }

    @Override
    public List<SysClientPOI> export() {
        LambdaQueryWrapper<SysClient> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysClient::getIsDeleted, 0);
        List<SysClient> sysClients = this.baseMapper.selectList(queryWrapper);
        return sysClients.stream().map(sysClient -> {
            SysClientPOI sysClientPOI = new SysClientPOI();
            BeanUtils.copyProperties(sysClient, sysClientPOI);
            return sysClientPOI;
        }).collect(Collectors.toList());
    }
}
