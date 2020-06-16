package vip.mate.core.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    NOT_FOUND(404,"服务未找到"),
    ERROR(500,"服务异常");

    /**
     * 状态码
     */
    final int code;
    /**
     * 消息内容
     */
    final String msg;
}
