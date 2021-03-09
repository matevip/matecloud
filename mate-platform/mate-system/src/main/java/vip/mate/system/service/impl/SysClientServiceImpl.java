package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.database.util.PageUtil;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysClient;
import vip.mate.system.mapper.SysClientMapper;
import vip.mate.system.poi.SysClientPOI;
import vip.mate.system.service.ISysClientService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
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
	public IPage<SysClient> listPage(Search search) {
		LambdaQueryWrapper<SysClient> queryWrapper = Wrappers.<SysClient>query().lambda()
				.between(StringUtil.isNotBlank(search.getStartDate()), SysClient::getCreateTime, search.getStartDate(), search.getEndDate());
		boolean isKeyword = StringUtil.isNotBlank(search.getKeyword());
		queryWrapper.like(isKeyword, SysClient::getClientId, search.getKeyword());
		queryWrapper.or(isKeyword);
		queryWrapper.like(isKeyword, SysClient::getId, search.getKeyword());
		return this.baseMapper.selectPage(PageUtil.getPage(search), queryWrapper);
	}

	@Override
	public boolean status(String ids, String status) {
		Collection<? extends Serializable> collection = CollectionUtil.stringToCollection(ids);

		for (Object id : collection) {
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
