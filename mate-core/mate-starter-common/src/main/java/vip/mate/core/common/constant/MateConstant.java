package vip.mate.core.common.constant;

import lombok.experimental.UtilityClass;

/**
 * Mate基本常量
 *
 * @author pangu
 */
@UtilityClass
public class MateConstant {

	/**
	 * 应用版本号
	 */
	public static final String MATE_APP_VERSION = "4.2.8";

	/**
	 * Spring 应用名 prop key
	 */
	public static final String SPRING_APP_NAME_KEY = "spring.application.name";


	/**
	 * 默认为空消息
	 */
	public static final String DEFAULT_NULL_MESSAGE = "承载数据为空";
	/**
	 * 默认成功消息
	 */
	public static final String DEFAULT_SUCCESS_MESSAGE = "处理成功";
	/**
	 * 默认失败消息
	 */
	public static final String DEFAULT_FAIL_MESSAGE = "处理失败";
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
	public static final String MATE_TRACE_ID = "mate-trace-id";

	/**
	 * 日志链路追踪id日志标志
	 */
	public static final String LOG_TRACE_ID = "traceId";

	/**
	 * Java默认临时目录
	 */
	public static final String JAVA_TEMP_DIR = "java.io.tmpdir";

	/**
	 * 版本
	 */
	public static final String VERSION = "version";

	/**
	 * 默认版本号
	 */
	public static final String DEFAULT_VERSION = "v1";

	/**
	 * 服务资源
	 */
	public static final String MATE_SERVICE_RESOURCE = "mate-service-resource";

	/**
	 * API资源
	 */
	public static final String MATE_API_RESOURCE = "mate-api-resource";

	/**
	 * 权限认证的排序
	 */
	public static final int MATE_UAA_FILTER_ORDER = -200;

	/**
	 * 签名排序
	 */
	public static final int MATE_SIGN_FILTER_ORDER = -300;

	/**
	 * json类型报文，UTF-8字符集
	 */
	public static final String JSON_UTF8 = "application/json;charset=UTF-8";


	public static final String CONFIG_DATA_ID_DYNAMIC_ROUTES = "mate-dynamic-routes.yaml";
	public static final String CONFIG_GROUP = "DEFAULT_GROUP";
	public static final long CONFIG_TIMEOUT_MS = 5000;


}
