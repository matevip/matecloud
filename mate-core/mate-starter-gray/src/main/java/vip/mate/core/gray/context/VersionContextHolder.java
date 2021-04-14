package vip.mate.core.gray.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.experimental.UtilityClass;

/**
 * 灰度版本工具类，支持父子线程传递参数
 *
 * @author madi
 * @date 2020-12-28 13:41
 */
@UtilityClass
public class VersionContextHolder {

	private final ThreadLocal<String> THREAD_LOCAL_VERSION = new TransmittableThreadLocal<>();

	/**
	 * TTL 设置灰度版本
	 *
	 * @param version
	 */
	public void setVersion(String version) {
		THREAD_LOCAL_VERSION.set(version);
	}


	/**
	 * 获取TTL中的灰度版本
	 *
	 * @return
	 */
	public String getVersion() {
		return THREAD_LOCAL_VERSION.get();
	}


	public void clear() {
		THREAD_LOCAL_VERSION.remove();
	}
}
