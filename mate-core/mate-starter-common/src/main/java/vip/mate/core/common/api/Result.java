package vip.mate.core.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import vip.mate.core.common.constant.MateConstant;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@Getter
@ApiModel(value = "统一响应消息报文")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态码", required = true)
    private String code;

    @ApiModelProperty(value = "消息内容", required = true)
    private String msg;

    @ApiModelProperty(value = "时间戳", required = true)
    private Instant time;

    @ApiModelProperty(value = "业务数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    private Result(IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private Result(IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private Result(IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private Result(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.time = ZonedDateTime.now().toInstant();
    }

    /**
     * 返回状态码
     * @param resultCode 状态码
     * @param <T> 泛型标识
     * @return ApiResult
     */
    public static <T> Result<T> success (IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.SUCCESS, msg);
    }

    public static <T> Result<T> success(IResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> data(T data) {
        return data(data, MateConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> Result<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    public static <T> Result<T> data(String code, T data, String msg) {
        return new Result<>(code, data, data == null ? MateConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    public static <T> Result<T> fail() {
        return new Result<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(ResultCode.FAILURE, msg);
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(code, null, msg);
    }

    public static <T> Result<T> fail(IResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> fail(IResultCode resultCode, String msg) {
        return new Result<>(resultCode, msg);
    }

    public static <T> Result<T> status(boolean flag) {
        return flag ? success(MateConstant.DEFAULT_SUCCESS_MESSAGE) : fail(MateConstant.DEFAULT_FAIL_MESSAGE);
    }
}
