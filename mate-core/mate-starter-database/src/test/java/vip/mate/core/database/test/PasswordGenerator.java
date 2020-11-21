package vip.mate.core.database.test;

import com.alibaba.druid.filter.config.ConfigTools;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PasswordGenerator {

	public static void main(String[] args) throws Exception {

		String password = "root";
		String[] arr = ConfigTools.genKeyPair(512);
		log.info("privateKey: {}", arr[0]);
		log.info("publicKey: {}", arr[1]);
		log.info("password: {}", ConfigTools.encrypt(arr[0], password));
	}
}
