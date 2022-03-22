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
package vip.mate.code.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.core.database.entity.BaseEntity;

/**
 * 代码生成基础表实体类
 *
 * @author pangu
 * @since 2022-03-21
 */
@Data
@TableName("mate_code_table")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Table对象", description = "代码生成基础表")
public class Table extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 表名称
	*/
	@ApiModelProperty(value = "表名称")
	private String name;
	/**
	* 表描述
	*/
	@ApiModelProperty(value = "表描述")
	private String comment;
	/**
	* 实体类名称
	*/
	@ApiModelProperty(value = "实体类名称")
	private String className;
	/**
	* 前缀名称
	*/
	@ApiModelProperty(value = "前缀名称")
	private String prefix;
	/**
	* 使用的模板
	*/
	@ApiModelProperty(value = "使用的模板")
	private String template;
	/**
	* 生成包路径
	*/
	@ApiModelProperty(value = "生成包路径")
	private String packageName;
	/**
	* 生成权限名
	*/
	@ApiModelProperty(value = "生成权限名")
	private String authorityName;
	/**
	* 生成模块名
	*/
	@ApiModelProperty(value = "生成模块名")
	private String moduleName;
	/**
	* 生成业务名
	*/
	@ApiModelProperty(value = "生成业务名")
	private String businessName;
	/**
	* 生成功能名
	*/
	@ApiModelProperty(value = "生成功能名")
	private String functionName;
	/**
	* 生成功能作者
	*/
	@ApiModelProperty(value = "生成功能作者")
	private String author;
	/**
	* 生成代码方式（0zip压缩包 1自定义路径）
	*/
	@ApiModelProperty(value = "生成代码方式（0zip压缩包 1自定义路径）")
	private String genType;
	/**
	* 后端生成路径
	*/
	@ApiModelProperty(value = "后端生成路径")
	private String genPath;
	/**
	* 前端生成路径
	*/
	@ApiModelProperty(value = "前端生成路径")
	private String uiPath;
	/**
	* 其它生成选项
	*/
	@ApiModelProperty(value = "其它生成选项")
	private String options;
	/**
	* 备注
	*/
	@ApiModelProperty(value = "备注")
	private String remark;

}
