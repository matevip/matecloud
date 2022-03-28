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

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vip.mate.core.database.entity.BaseEntity;

/**
 * 代码生成字段表实体类
 *
 * @author pangu
 * @since 2022-03-21
 */
@Data
@TableName("mate_code_column")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Column对象", description = "代码生成字段表")
public class Column extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 归属表Id
	*/
	@ApiModelProperty(value = "归属表Id")
	private String tableId;
	/**
	* 列名称
	*/
	@ApiModelProperty(value = "列名称")
	private String name;
	/**
	* 列描述
	*/
	@ApiModelProperty(value = "列描述")
	private String comment;
	/**
	* 列类型
	*/
	@ApiModelProperty(value = "列类型")
	private String type;
	/**
	* JAVA类型
	*/
	@ApiModelProperty(value = "JAVA类型")
	private String javaType;
	/**
	* JAVA字段名
	*/
	@ApiModelProperty(value = "JAVA字段名")
	private String javaField;
	/**
	* 主键字段（1是 0否）
	*/
	@ApiModelProperty(value = "主键字段（1是 0否）")
	private Boolean isPk;
	/**
	* 自增字段（1是 0否）
	*/
	@ApiModelProperty(value = "自增字段（1是 0否）")
	private Boolean isIncrement;
	/**
	* 必填字段（1是 0否）
	*/
	@ApiModelProperty(value = "必填字段（1是 0否）")
	private Boolean isRequired;
	/**
	* 查看字段（1是 0否）
	*/
	@ApiModelProperty(value = "查看字段（1是 0否）")
	private Boolean isView;
	/**
	* 新增字段（1是 0否）
	*/
	@ApiModelProperty(value = "新增字段（1是 0否）")
	private Boolean isInsert;
	/**
	* 编辑字段（1是 0否）
	*/
	@ApiModelProperty(value = "编辑字段（1是 0否）")
	private Boolean isEdit;
	/**
	* 列表字段（1是 0否）
	*/
	@ApiModelProperty(value = "列表字段（1是 0否）")
	private Boolean isList;
	/**
	* 查询字段（1是 0否）
	*/
	@ApiModelProperty(value = "查询字段（1是 0否）")
	private Boolean isQuery;
	/**
	* 唯一字段（1是 0否）
	*/
	@ApiModelProperty(value = "唯一字段（1是 0否）")
	private Boolean isUnique;
	/**
	* 导入字段（1是 0否）
	*/
	@ApiModelProperty(value = "导入字段（1是 0否）")
	private Boolean isImport;
	/**
	* 导出字段（1是 0否）
	*/
	@ApiModelProperty(value = "导出字段（1是 0否）")
	private Boolean isExport;
	/**
	* 隐藏字段（1是 0否）
	*/
	@ApiModelProperty(value = "隐藏字段（1是 0否）")
	private Boolean isHide;
	/**
	* 覆盖字段（1是 0否）
	*/
	@ApiModelProperty(value = "覆盖字段（1是 0否）")
	private Boolean isCover;
	/**
	* 查询方式（等于、不等于、大于、小于、范围）
	*/
	@ApiModelProperty(value = "查询方式（等于、不等于、大于、小于、范围）")
	private String queryType;
	/**
	* 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
	*/
	@ApiModelProperty(value = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
	private String htmlType;
	/**
	* 字典类型
	*/
	@ApiModelProperty(value = "字典类型")
	private String dictType;
	/**
	* 显示顺序
	*/
	@ApiModelProperty(value = "显示顺序")
	private Integer sort;

	public String readNameNoSuffix() {
		return StrUtil.isNotEmpty(this.getComment()) ? this.getComment().replaceAll("(?:\\（)[^\\(\\)]*(?:\\）)", StrUtil.EMPTY) : this.getComment();
	}

}
