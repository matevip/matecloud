/*
 * Copyright 2019-2028 Beijing Daotiandi Technology Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Author: xuzhanfu (7333791@qq.com)
 */
package vip.mate.common.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import vip.mate.common.constant.MateConstant;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Optional;

/**
 * 响应信息主体封装
 *
 * @author xuzhanfu
 * @date 2019-10-08 23:07
 **/

@ToString
@Data
@NoArgsConstructor
@ApiModel(value = "响应信息主体")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1679552421651455773L;

    @ApiModelProperty(value = "状态码", required = true)
    private int code;
    @ApiModelProperty(value = "业务数据")
    private T data;
    @ApiModelProperty(value = "返回内容", required = true)
    private String message;
    @ApiModelProperty(value = "请求URL")
    private String url;
    @ApiModelProperty(value = "根服务")
    private String host;
    @ApiModelProperty(value = "是否成功", required = true)
    private boolean success;

    private R(IStatusCode statusCode) {
        this(statusCode, null, statusCode.getMessage());
    }

    private R(IStatusCode statusCode, String message) {
        this(statusCode, null, message);
    }

    private R(IStatusCode statusCode, T data) {
        this(statusCode, data, statusCode.getMessage());
    }

    private R(IStatusCode statusCode, T data, String message) {
        this(statusCode.getCode(), data, message);
    }

    private R(IStatusCode statusCode, T data, String message, String url, String host){
        this(statusCode.getCode(), data, message, url, host);
    }

    private R(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.success = StatusCode.SUCCESS.code == code;
    }

    private R(int code, T data, String message, String url, String host) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.url = url;
        this.host = host;
        this.success = StatusCode.SUCCESS.code == code;
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isSuccess(@Nullable R<?> result) {
        return Optional.ofNullable(result)
                .map(x -> ObjectUtils.nullSafeEquals(StatusCode.SUCCESS.code, x.code))
                .orElse(Boolean.FALSE);
    }

    /**
     * 判断返回是否为成功
     *
     * @param result Result
     * @return 是否成功
     */
    public static boolean isNotSuccess(@Nullable R<?> result) {
        return !R.isSuccess(result);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data) {
        return data(data, MateConstant.DEFAULT_SUCCESS_MESSAGE);
    }

    /**
     * 返回R
     *
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(T data, String msg) {
        return data(HttpServletResponse.SC_OK, data, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param data 数据
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> data(int code, T data, String msg) {
        return new R<>(code, data, data == null ? MateConstant.DEFAULT_NULL_MESSAGE : msg);
    }

    /**
     * 返回R
     * @param code
     * @param data
     * @param msg
     * @param url
     * @param host
     * @param <T>
     * @return　R
     */
    public static <T> R<T> data(int code, T data, String msg, String url, String host) {
        return new R<>(code, data, data == null ? MateConstant.DEFAULT_NULL_MESSAGE : msg, url, host);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> success(String msg) {
        return new R<>(StatusCode.SUCCESS, msg);
    }

    /**
     * 返回R
     *
     * @param statusCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IStatusCode statusCode) {
        return new R<>(statusCode);
    }

    /**
     * 返回R
     *
     * @param statusCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> success(IStatusCode statusCode, String msg) {
        return new R<>(statusCode, msg);
    }

    /**
     * 返回R
     *
     * @param msg 消息
     * @param <T> T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(String msg) {
        return new R<>(StatusCode.FAILURE, msg);
    }

    /**
     * 返回R
     *
     * @param code 状态码
     * @param msg  消息
     * @param <T>  T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, null, msg);
    }

    /**
     * 返回R
     *
     * @param statusCode 业务代码
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IStatusCode statusCode) {
        return new R<>(statusCode);
    }

    /**
     * 返回R
     *
     * @param statusCode 业务代码
     * @param msg        消息
     * @param <T>        T 泛型标记
     * @return R
     */
    public static <T> R<T> fail(IStatusCode statusCode, String msg) {
        return new R<>(statusCode, msg);
    }

    /**
     * 返回R
     *
     * @param flag 成功状态
     * @return R
     */
    public static <T> R<T> status(boolean flag) {
        return flag ? success(MateConstant.DEFAULT_SUCCESS_MESSAGE) : fail(MateConstant.DEFAULT_FAIL_MESSAGE);
    }
}
