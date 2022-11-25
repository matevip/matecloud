/*
 * Copyright 2020-2030, MateCloud, DAOTIANDI Technology Inc All rights reserved.
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
 * Author: pangu(7333791@qq.com)
 */
package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.core.database.entity.BaseEntity;

/**
 * 客户端表实体类
 *
 * @author pangu
 * @since 2020-07-14
 */
@Data
@TableName("mate_sys_client")
@EqualsAndHashCode(callSuper = true)
@Schema(name = "SysClient对象", description = "客户端表")
public class SysClient extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 客户端id
	*/
	@Schema(description = "客户端标识")
	private String clientId;
	/**
	* 客户端密钥
	*/
	@Schema(description = "客户端密钥")
	private String clientSecret;
	/**
	* 资源集合
	*/
	@Schema(description = "资源集合")
	private String resourceIds;
	/**
	* 授权范围
	*/
	@Schema(description = "授权范围")
	private String scope;
	/**
	* 授权类型
	*/
	@Schema(description = "授权类型")
	private String authorizedGrantTypes;
	/**
	* 回调地址
	*/
	@Schema(description = "回调地址")
	private String webServerRedirectUri;
	/**
	* 权限
	*/
	@Schema(description = "权限")
	private String authorities;
	/**
	* 令牌过期秒数
	*/
	@Schema(description = "令牌过期秒数")
	private Integer accessTokenValidity;
	/**
	* 刷新令牌过期秒数
	*/
	@Schema(description = "刷新令牌过期秒数")
	private Integer refreshTokenValidity;
	/**
	* 附加说明
	*/
	@Schema(description = "附加说明")
	private String additionalInformation;
	/**
	* 自动授权
	*/
	@Schema(description = "自动授权")
	private String autoapprove;
	/**
	* 状态
	*/
	@Schema(description = "状态")
	private String status;
	/**
	* 是否已删除
	*/
	@Schema(description = "是否已删除")
	private Integer isDeleted;


}
