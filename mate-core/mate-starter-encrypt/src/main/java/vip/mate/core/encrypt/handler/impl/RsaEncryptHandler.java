package vip.mate.core.encrypt.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import vip.mate.core.encrypt.entity.RsaKey;
import vip.mate.core.encrypt.exception.EncryptException;
import vip.mate.core.encrypt.handler.EncryptHandler;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * RSA加密处理器
 *
 * @author pangu
 */
@Data
@Slf4j
public class RsaEncryptHandler implements EncryptHandler {

	private static final String KEY_ALGORITHM = "RSA";
	private static final int KEY_SIZE = 1024;
	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	private static final int MAX_ENCODE_BLOCK = (KEY_SIZE / 8) - 11;
	private static final int MAX_DECODE_BLOCK = KEY_SIZE / 8;
	private String publicKey;
	private String privateKey;

	@Override
	public byte[] encode(byte[] content) {
		try {
			byte[] bytes = encryptByPublicKey(content, Base64Utils.decodeFromString(publicKey));
			return bytes;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new EncryptException("rsa加密错误", e);
		}
	}

	@Override
	public byte[] decode(byte[] content) {
		try {
			byte[] bytes = decryptByPrivateKey(Base64Utils.decode(content), Base64Utils.decodeFromString(privateKey));
			return bytes;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new EncryptException("rsa解密错误", e);
		}
	}
	public static RsaKey getRsaKeys() throws Exception {
		Map<String, Object> keyMap = initKey();
		byte[] publicKey = getPublicKey(keyMap);
		byte[] privateKey = getPrivateKey(keyMap);
		RsaKey rsaKey = new RsaKey();
		rsaKey.setPublicKey(Base64Utils.encodeToString(publicKey));
		rsaKey.setPrivateKey(Base64Utils.encodeToString(privateKey));
		return rsaKey;
	}

	private static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGenerator.initialize(KEY_SIZE);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;

	}

	private static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		byte[] encryptedData = new byte[0];
		if (data.length == 0) {
			return encryptedData;
		}
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);

			encryptedData = Base64Utils.encode(doFinal(data, cipher, out, MAX_ENCODE_BLOCK));
		}
		return encryptedData;
	}

	private static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		byte[] encryptedData = new byte[0];
		if (data.length == 0) {
			return encryptedData;
		}
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
			PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);

			encryptedData = Base64Utils.encode(doFinal(data, cipher, out, MAX_ENCODE_BLOCK));
		}
		return encryptedData;
	}

	private static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		byte[] encryptedData = new byte[0];
		if (data.length == 0) {
			return encryptedData;
		}
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			encryptedData = doFinal(data, cipher, out, MAX_DECODE_BLOCK);
		}
		return encryptedData;
	}

	private static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
		byte[] encryptedData = new byte[0];
		if (data.length == 0) {
			return encryptedData;
		}
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key);
			PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, pubKey);

			encryptedData = doFinal(data, cipher, out, MAX_DECODE_BLOCK);
		}
		return encryptedData;
	}

	private static byte[] getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}

	private static byte[] getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}
	private static byte[] doFinal(byte[] data, Cipher cipher, ByteArrayOutputStream out, int MAX_BLOCK) throws BadPaddingException, IllegalBlockSizeException {
		int inputLen = data.length;
		int offSet = 0;
		byte[] cache;
		int i = 0;
		while (inputLen - offSet > 0) {
			if (inputLen - offSet > MAX_BLOCK) {
				cache = cipher.doFinal(data, offSet, MAX_BLOCK);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}
			out.write(cache, 0, cache.length);
			i++;
			offSet = i * MAX_BLOCK;
		}
		return out.toByteArray();
	}
}
