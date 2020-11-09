package vip.mate.core.encrypt.handler;

import vip.mate.core.encrypt.exception.EncryptException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 签名加密处理类
 *
 * @author gaoyang
 * @author pangu
 */
public interface SignEncryptHandler {

	/**
	 * 签名处理
	 *
	 * @param proceed    处理对象
	 * @param timeout    超时时间
	 * @param timeUnit   时间戳
	 * @param signSecret 加密密钥
	 * @param jsonMap    加密报文
	 * @return Object
	 * @throws EncryptException 加密异常
	 */
	public Object handle(Object proceed, long timeout, TimeUnit timeUnit, String signSecret,
	                     Map<Object, Object> jsonMap) throws EncryptException;
}
