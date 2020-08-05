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
package vip.mate.component.entity;

import vip.mate.core.database.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 配置表实体类
 *
 * @author pangu
 * @since 2020-08-05
 */
@Data
@TableName("mate_sys_config")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysConfig对象", description = "配置表")
public class SysConfig extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 父主键
	*/
	@ApiModelProperty(value = "父主键")
	private Long parentId;
	/**
	* 码
	*/
	@ApiModelProperty(value = "码")
	private String code;
	/**
	* 值
	*/
	@ApiModelProperty(value = "值")
	private String key;
	/**
	* 名称
	*/
	@ApiModelProperty(value = "名称")
	private String value;
	/**
	* 排序
	*/
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	* 备注
	*/
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	* 创建人
	*/
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**
	* 更新人
	*/
	@ApiModelProperty(value = "更新人")
	private String updateBy;
	/**
	* 创建时间
	*/
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;
	/**
	* 修改时间
	*/
	@ApiModelProperty(value = "修改时间")
	private LocalDateTime updateTime;
	private Integer tenantId;
	/**
	* 是否已删除
	*/
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;


}
