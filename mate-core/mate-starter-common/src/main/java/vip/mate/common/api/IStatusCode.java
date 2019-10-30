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

import java.io.Serializable;

/**
 * 业务状态码接口
 *
 * @author xuzhanfu
 * @date 2019-10-09 09:28
 **/
public interface IStatusCode extends Serializable {

    /**
     * 获取状态码
     * @return
     */
    int getCode();

    /**
     * 获取消息内容
     * @return
     */
    String getMessage();


}
