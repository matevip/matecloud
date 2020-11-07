package vip.mate.core.encrypt.handler.impl;

import org.springframework.util.Base64Utils;
import vip.mate.core.encrypt.handler.EncryptHandler;

/**
 * Base64加密处理器
 *
 * @author gaoyang
 */
public class Base64EncryptHandler implements EncryptHandler {

	@Override
	public byte[] encode(byte[] content) {
		return Base64Utils.encode(content);
	}

	@Override
	public byte[] decode(byte[] content) {
		return Base64Utils.decode(content);
	}
}
