package vip.mate.core.rule.service;

import vip.mate.core.rule.entity.BlackList;

import java.util.Set;

/**
 * 规则缓存业务
 * @author pangu
 */
public interface IRuleCacheService {

    Set<Object> getBlackList(String ip);

    Set<Object> getBlackList();

    void setBlackList(BlackList blackList);

    void deleteBlackList(BlackList blackList);
}
