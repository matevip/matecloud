package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import vip.mate.core.common.util.StringUtil;
import vip.mate.core.database.entity.Search;
import vip.mate.system.entity.SysBlacklist;
import vip.mate.system.mapper.SysBlacklistMapper;
import vip.mate.system.service.ISysBlacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统黑名单表 服务实现类
 * </p>
 *
 * @author pangu
 * @since 2020-08-26
 */
@Service
public class SysBlacklistServiceImpl extends ServiceImpl<SysBlacklistMapper, SysBlacklist> implements ISysBlacklistService {

    @Override
    public Page listPage(Page page, Search search) {
        LambdaQueryWrapper<SysBlacklist> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotBlank(search.getStartDate())) {
            lambdaQueryWrapper.between(SysBlacklist::getCreateTime, search.getStartDate(), search.getEndDate());
        }
        String keyword = search.getKeyword();
        if (StringUtils.isNotBlank(keyword) && !keyword.equals("null")) {
            lambdaQueryWrapper.like(SysBlacklist::getId, keyword);
            lambdaQueryWrapper.or();
            lambdaQueryWrapper.like(SysBlacklist::getRequestUri, keyword);
        }
        return this.baseMapper.selectPage(page, lambdaQueryWrapper);
    }
}
