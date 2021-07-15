package vip.mate.core.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁实现基于Redisson
 *
 * @author pangu
 * @date 2020-10-22
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RedissonLock {

	RedissonManager redissonManager;

	/**
	 * 加锁操作
	 *
	 * @return boolean
	 */
	public boolean lock(String lockName, long expireSeconds) {
		RLock rLock = redissonManager.getRedisson().getLock(lockName);
		boolean getLock;
		try {
			getLock = rLock.tryLock(0, expireSeconds, TimeUnit.SECONDS);
			if (getLock) {
				log.info("获取Redisson分布式锁[成功],lockName={}", lockName);
			} else {
				log.info("获取Redisson分布式锁[失败],lockName={}", lockName);
			}
		} catch (InterruptedException e) {
			log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
			e.printStackTrace();
			return false;
		}
		return getLock;
	}

	/**
	 * 解锁
	 *
	 * @param lockName 锁名称
	 */
	public void release(String lockName) {
		redissonManager.getRedisson().getLock(lockName).unlock();
	}


}
