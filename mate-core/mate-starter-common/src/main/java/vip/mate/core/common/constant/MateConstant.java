package vip.mate.core.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConstant {

    /**
     * 应用版本号
     */
    public static final String MATE_APP_VERSION = "0.8.8";

    /**
     * Spring 应用名 prop key
     */
    public static final String SPRING_APP_NAME_KEY = "spring.application.name";

    /**
     * request id mdc key
     */
    public static final String REQUEST_ID_MDC_KEY = "mdcReqId";

    /**
     * request id key
     */
    public static final String REQUEST_ID_KEY = "Mate-Request-Id";

    public static final String START_TIME_KEY = "Mate-ST-Id";

    /**
     * 默认为空消息
     */
    public static final String DEFAULT_NULL_MESSAGE = "承载数据为空";
    /**
     * 默认成功消息
     */
    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    /**
     * 默认失败消息
     */
    public static final String DEFAULT_FAIL_MESSAGE = "操作失败";
    /**
     * 树的根节点值
     */
    public static final Long TREE_ROOT = -1L;
    /**
     * 允许的文件类型，可根据需求添加
     */
    public static final String[] VALID_FILE_TYPE = {"xlsx", "zip"};

    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 微服务之间传递的唯一标识
     */
    public static final String X_REQUEST_ID = "X-Request-Id";

    /**
     * Java默认临时目录
     */
    public static final String JAVA_TEMP_DIR = "java.io.tmpdir";


}
