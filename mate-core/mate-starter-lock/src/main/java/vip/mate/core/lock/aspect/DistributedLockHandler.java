package vip.mate.core.lock.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vip.mate.core.lock.RedissonLock;
import vip.mate.core.lock.annotation.DistributedLock;

/**
 * 分布式锁解析器
 *
 * @author pangu
 * @date 2020-10-22
 * @link https://github.com/TaXueWWL/redis-distributed-lock
 */
@Slf4j
@Aspect
@Component
public class DistributedLockHandler {

	@Autowired
	RedissonLock redissonLock;

	@Pointcut("@annotation(vip.mate.core.lock.annotation.DistributedLock)")
	public void distributedLock() {
	}

	@Around("@annotation(distributedLock)")
	public void around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
		log.info("[开始]执行RedisLock环绕通知,获取Redis分布式锁开始");
		//获取锁名称
		String lockName = distributedLock.value();
		//获取超时时间
		int expireSeconds = distributedLock.expireSeconds();

		if (redissonLock.lock(lockName, expireSeconds)) {
			try {
				log.info("获取Redis分布式锁[成功]，加锁完成，开始执行业务逻辑...");
				joinPoint.proceed();
			} catch (Throwable throwable) {
				log.error("获取Redis分布式锁[异常]，加锁失败", throwable);
				throwable.printStackTrace();
			} finally {
				redissonLock.release(lockName);
			}
			log.info("释放Redis分布式锁[成功]，解锁完成，结束业务逻辑...");
		} else {
			log.error("获取Redis分布式锁[失败]");
		}
		log.info("[结束]执行RedisLock环绕通知");

	}
}
