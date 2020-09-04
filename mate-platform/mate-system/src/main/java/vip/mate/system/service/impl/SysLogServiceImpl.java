package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
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
    public IPage<SysLog> listPage(Page page, Search search) {
        LambdaQueryWrapper<SysLog> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            queryWrapper.between(SysLog::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        if (StringUtil.isNotBlank(search.getKeyword())) {
            queryWrapper.like(SysLog::getTitle, search.getKeyword());
            queryWrapper.or();
            queryWrapper.like(SysLog::getId, search.getKeyword());
        }
        queryWrapper.orderByDesc(SysLog::getCreateTime);
        return this.baseMapper.selectPage(page, queryWrapper);
    }
}
