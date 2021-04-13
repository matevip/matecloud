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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.core.database.entity.BaseEntity;

/**
 * 角色和部门关联表实体类
 *
 * @author pangu
 * @since 2021-04-05
 */
@Data
@TableName("mate_sys_role_depart")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysRoleDepart对象", description = "角色和部门关联表")
public class SysRoleDepart extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 角色ID
	*/
	@ApiModelProperty(value = "角色ID")
	private Long roleId;
	/**
	* 部门ID
	*/
	@ApiModelProperty(value = "部门ID")
	private Long departId;


}
