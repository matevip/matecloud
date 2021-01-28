package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.core.database.util.PageUtil;
import vip.mate.system.entity.SysLog;
import vip.mate.system.mapper.SysLogMapper;
import vip.mate.system.service.ISysLogService;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-07-15
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

	@Override
	public IPage<SysLog> listPage(Search search) {
		boolean isKeyword = StringUtil.isNotBlank(search.getKeyword());
		LambdaQueryWrapper<SysLog> queryWrapper = Wrappers.lambdaQuery();
		// 查询开始日期和结束日期
		queryWrapper.between(StringUtil.isNotBlank(search.getStartDate()), SysLog::getCreateTime, search.getStartDate(), search.getEndDate());
		// 关键词查询
		queryWrapper.like(isKeyword, SysLog::getTitle, search.getKeyword()).or(isKeyword).like(isKeyword, SysLog::getTraceId, search.getKeyword());
		//　字段排序
		queryWrapper.orderByDesc(SysLog::getCreateTime);
		return this.baseMapper.selectPage(PageUtil.getPage(search), queryWrapper);
	}
}
