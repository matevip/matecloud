package vip.mate.core.encrypt.handler.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import vip.mate.core.encrypt.handler.EncryptHandler;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密处理器
 *
 * @author pangu
 */
@Slf4j
public class AesEncryptHandler implements EncryptHandler {

	private String secret;

	private static final String VIPARA = "0102030405060708";
	private static final String KEY_ALGORITHM = "AES";

	@Override
	public byte[] encode(byte[] content) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] byte_AES = cipher.doFinal(content);
			return Base64Utils.encode(byte_AES);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new byte[0];
	}

	@Override
	public byte[] decode(byte[] content) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] byte_content = Base64Utils.decode(content);
			byte[] byte_decode = cipher.doFinal(byte_content);
			return byte_decode;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new byte[0];
	}
}
