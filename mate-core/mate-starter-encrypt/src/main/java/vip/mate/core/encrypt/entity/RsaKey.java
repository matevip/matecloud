package vip.mate.core.encrypt.entity;

import lombok.Data;

/**
 * RSA公私钥实体类
 *
 * @author gaoyang
 */
@Data
public class RsaKey {

	private String publicKey;
	private String privateKey;

}
