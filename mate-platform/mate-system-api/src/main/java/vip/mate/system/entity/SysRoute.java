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

import vip.mate.core.database.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统路由表实体类
 *
 * @author pangu
 * @since 2020-10-17
 */
@Data
@TableName("mate_sys_route")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRoute对象", description = "系统路由表")
public class SysRoute extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 接口名称
	*/
	@ApiModelProperty(value = "接口名称")
	private String name;
	/**
	* 路径前缀
	*/
	@ApiModelProperty(value = "路径前缀")
	private String path;
	/**
	* 地址
	*/
	@ApiModelProperty(value = "地址")
	private String url;
	/**
	* 服务ID
	*/
	@ApiModelProperty(value = "服务ID")
	private String serviceId;
	/**
	* API状态:0:禁用 1:启用
	*/
	@ApiModelProperty(value = "API状态:0:禁用 1:启用")
	private String status;
	/**
	* 删除标识
	*/
	@ApiModelProperty(value = "删除标识")
	private String isDeleted;
	/**
	* 租户ID
	*/
	@ApiModelProperty(value = "租户ID")
	private Integer tenantId;


}
