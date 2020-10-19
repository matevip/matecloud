package vip.mate.core.rule.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.mate.core.redis.core.RedisService;
import vip.mate.core.rule.constant.RuleConstant;
import vip.mate.core.rule.entity.BlackList;
import vip.mate.core.rule.service.IRuleCacheService;

import java.util.Set;

/**
 * 规则缓存实现业务类
 * @author pangu
 */
@Service
public class RuleCacheServiceImpl implements IRuleCacheService {

    @Autowired
    private RedisService redisService;

    @Override
    public Set<Object> getBlackList(String ip) {
        return redisService.sGet(RuleConstant.getBlackListCacheKey(ip));
    }

    @Override
    public Set<Object> getBlackList() {
        return redisService.sGet(RuleConstant.getBlackListCacheKey());
    }

    @Override
    public void setBlackList(BlackList blackList) {
        String key = StringUtils.isNotBlank(blackList.getIp()) ? RuleConstant.getBlackListCacheKey(blackList.getIp())
                : RuleConstant.getBlackListCacheKey();
        redisService.sSet(key, JSONObject.toJSONString(blackList));
    }

    @Override
    public void deleteBlackList(BlackList blackList) {
        String key = StringUtils.isNotBlank(blackList.getIp()) ? RuleConstant.getBlackListCacheKey(blackList.getIp())
                : RuleConstant.getBlackListCacheKey();
        redisService.setRemove(key, JSONObject.toJSONString(blackList));
    }
}
