package vip.mate.core.encrypt.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import vip.mate.core.encrypt.exception.EncryptException;
import vip.mate.core.encrypt.handler.EncryptHandler;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密处理器
 *
 * @author pangu
 */
@Data
@Slf4j
public class AesEncryptHandler implements EncryptHandler {

	private String secret;

	private static final String IV_PARA = "0102030405060708";
	private static final String KEY_ALGORITHM = "AES";

	@Override
	public byte[] encode(byte[] content) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(IV_PARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			byte[] byteAes = cipher.doFinal(content);
			return Base64Utils.encode(byteAes);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new EncryptException("rsa加密错误", e);
		}
	}

	@Override
	public byte[] decode(byte[] content) {
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(IV_PARA.getBytes());
			SecretKeySpec key = new SecretKeySpec(secret.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
			byte[] byteContent = Base64Utils.decode(content);
			return cipher.doFinal(byteContent);
		} catch (Exception e) {
			throw new EncryptException("rsa加密错误", e);
		}
	}
}
