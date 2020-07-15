package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.system.entity.SysClient;
import vip.mate.system.entity.SysLog;
import vip.mate.system.mapper.SysLogMapper;
import vip.mate.system.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public IPage<SysLog> listPage(Map<String, String> query) {
        long current = CollectionUtil.strToLong(query.get("current"), 0L);
        long size = CollectionUtil.strToLong(query.get("size"), 0L);
        IPage<SysLog> page = new Page<>(current, size);

        String keyword = String.valueOf(query.get("keyword"));
        String startDate = String.valueOf(query.get("startDate"));
        String endDate = String.valueOf(query.get("endDate"));
        LambdaQueryWrapper<SysLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(startDate) && !startDate.equals("null")) {
            lambdaQueryWrapper.between(SysLog::getCreateTime, startDate, endDate);
        }
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysLog::getTitle, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysLog::getId, keyword);
        }
        lambdaQueryWrapper.orderByDesc(SysLog::getCreateTime);
        return this.baseMapper.selectPage(page, lambdaQueryWrapper);
    }
}
