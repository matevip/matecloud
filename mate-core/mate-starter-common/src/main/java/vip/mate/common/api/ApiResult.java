package vip.mate.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import vip.mate.common.constant.MateConstant;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

@Data
@Getter
@ApiModel(value = "统一响应消息报文")
public class ApiResult<T> implements Serializable {

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

    private ApiResult() {
        this.time = ZonedDateTime.now().toInstant();
    }

    private ApiResult (IResultCode resultCode) {
        this(resultCode, null, resultCode.getMsg());
    }

    private ApiResult (IResultCode resultCode, String msg) {
        this(resultCode, null, msg);
    }

    private ApiResult (IResultCode resultCode, T data) {
        this(resultCode, data, resultCode.getMsg());
    }

    private ApiResult (IResultCode resultCode, T data, String msg) {
        this(resultCode.getCode(), data, msg);
    }

    private ApiResult (String code, T data, String msg) {
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
    public static <T> ApiResult<T> success (IResultCode resultCode) {
        return new ApiResult<>(resultCode);
    }

    public static <T> ApiResult<T> success(String msg) {
        return new ApiResult<>(ResultCode.SUCCESS, msg);
    }

    public static <T> ApiResult<T> success(IResultCode resultCode, String msg) {
        return new ApiResult<>(resultCode, msg);
    }

    public static <T> ApiResult<T> data(T data) {
        return data(data, MateConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> ApiResult<T> data(T data, String msg) {
        return data(ResultCode.SUCCESS.code, data, msg);
    }

    public static <T> ApiResult<T> data(String code, T data, String msg) {
        return new ApiResult<>(code, data, data == null ? MateConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    public static <T> ApiResult<T> fail() {
        return new ApiResult<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
    }

    public static <T> ApiResult<T> fail(String msg) {
        return new ApiResult<>(ResultCode.FAILURE, msg);
    }

    public static <T> ApiResult<T> fail(String code, String msg) {
        return new ApiResult<>(code, null, msg);
    }

    public static <T> ApiResult<T> fail(IResultCode resultCode) {
        return new ApiResult<>(resultCode);
    }

    public static <T> ApiResult<T> fail(IResultCode resultCode, String msg) {
        return new ApiResult<>(resultCode, msg);
    }

    public static <T> ApiResult<T> status(boolean flag) {
        return flag ? success(MateConstant.DEFAULT_SUCCESS_MESSAGE) : fail(MateConstant.DEFAULT_FAIL_MESSAGE);
    }
}
