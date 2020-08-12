package vip.mate.core.common.exception;

/**
 * 演示环境异常
 * @author pangu
 */
public class PreviewException extends RuntimeException {

    private static final long serialVersionUID = 6889855134686307145L;

    public PreviewException (String message) {
        super(message);
    }
}
