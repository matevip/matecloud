package vip.mate.core.encrypt.exception;

/**
 * 加密自定义异常
 *
 * @author gaoyang
 */
public class EncryptException extends RuntimeException {

	public EncryptException() {
		super();
	}

	public EncryptException(String message) {
		super(message);
	}

	public EncryptException(String message, Throwable t) {
		super(message, t);
	}
}
