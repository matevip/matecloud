package vip.mate.core.common.constant;

/**
 * 网络通信常量，包括枚举类
 *
 * @author david
 */
public class WebConstant {

    /**
     * json类型报文，UTF-8字符集
     */
    public static final String JSON_UTF8 = "application/json;charset=UTF-8";

    /**
     * ws请求匹配头
     */
    public static final String DEFAULT_FILTER_PATH = "/ws/";

    /**
     * ws请求端口偏移
     */
    public static final int WS_PORT = 10;

    /**
     * 字符集
     */
    public enum Character {

        UTF8("UTF-8", "UTF-8"),
        GBK("GBK", "GBK");

        private final String code;
        private final String info;

        Character(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 返回类型
     */
    public enum ResultType {
        SUCCESS("success", "操作成功"),
        ERROR("error", "操作失败"),
        WARNING("warning", "操作异常");

        private final String code;
        private final String info;

        ResultType(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 请求类型
     */
    public enum Type {

        LOOKUP_RMI("rmi:", "RMI 远程方法调用"),
        LOOKUP_LDAP("ldap:", "LDAP 远程方法调用"),
        LOOKUP_LDAPS("ldaps:", "LDAPS 远程方法调用"),
        HTTP("http://", "http请求"),
        HTTPS("https://", "https请求"),
        WS("ws://", "ws请求"),
        WSS("wss://", "wss请求");

        private final String code;
        private final String info;

        Type(String code, String info) {
            this.code = code;
            this.info = info;
        }

        public String getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }

    /**
     * 返回状态码
     */
    public enum Status {

        SUCCESS(200, "操作成功"),
        CREATED(201, "对象创建成功"),
        ACCEPTED(202, "请求已经被接受"),
        NO_CONTENT(204, "操作已经执行成功，但是没有返回数据"),
        MOVED_PERM(301, "资源已被移除"),
        SEE_OTHER(303, "重定向"),
        NOT_MODIFIED(304, "资源没有被修改"),
        BAD_REQUEST(400, "参数列表错误（缺少，格式不匹配）"),
        UNAUTHORIZED(401, "未授权"),
        FORBIDDEN(403, "访问受限，授权过期"),
        NOT_FOUND(404, "资源，服务未找到"),
        BAD_METHOD(405, "不允许的http方法"),
        CONFLICT(409, "资源冲突，或者资源被锁"),
        UNSUPPORTED_TYPE(415, "不支持的数据，媒体类型"),
        INTERNAL_SERVER_ERROR(500, "系统内部错误"),
        NOT_IMPLEMENTED(501, "接口未实现");

        private final int code;
        private final String info;

        Status(int code, String info) {
            this.code = code;
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public String getInfo() {
            return info;
        }
    }
}
