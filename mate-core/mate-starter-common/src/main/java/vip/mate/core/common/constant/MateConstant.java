package vip.mate.core.common.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MateConstant {

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

}
