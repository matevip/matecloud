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
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Column对象", description = "代码生成字段表")
public class Column extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 归属表Id
	*/
	@Schema(description = "归属表Id")
	private String tableId;
	/**
	* 列名称
	*/
	@Schema(description = "列名称")
	private String name;
	/**
	* 列描述
	*/
	@Schema(description = "列描述")
	private String comment;
	/**
	* 列类型
	*/
	@Schema(description = "列类型")
	private String type;
	/**
	* JAVA类型
	*/
	@Schema(description = "JAVA类型")
	private String javaType;
	/**
	* JAVA字段名
	*/
	@Schema(description = "JAVA字段名")
	private String javaField;
	/**
	* 主键字段（1是 0否）
	*/
	@Schema(description = "主键字段（1是 0否）")
	private Boolean isPk;
	/**
	* 自增字段（1是 0否）
	*/
	@Schema(description = "自增字段（1是 0否）")
	private Boolean isIncrement;
	/**
	* 必填字段（1是 0否）
	*/
	@Schema(description = "必填字段（1是 0否）")
	private Boolean isRequired;
	/**
	* 查看字段（1是 0否）
	*/
	@Schema(description = "查看字段（1是 0否）")
	private Boolean isView;
	/**
	* 新增字段（1是 0否）
	*/
	@Schema(description = "新增字段（1是 0否）")
	private Boolean isInsert;
	/**
	* 编辑字段（1是 0否）
	*/
	@Schema(description = "编辑字段（1是 0否）")
	private Boolean isEdit;
	/**
	* 列表字段（1是 0否）
	*/
	@Schema(description = "列表字段（1是 0否）")
	private Boolean isList;
	/**
	* 查询字段（1是 0否）
	*/
	@Schema(description = "查询字段（1是 0否）")
	private Boolean isQuery;
	/**
	* 唯一字段（1是 0否）
	*/
	@Schema(description = "唯一字段（1是 0否）")
	private Boolean isUnique;
	/**
	* 导入字段（1是 0否）
	*/
	@Schema(description = "导入字段（1是 0否）")
	private Boolean isImport;
	/**
	* 导出字段（1是 0否）
	*/
	@Schema(description = "导出字段（1是 0否）")
	private Boolean isExport;
	/**
	* 隐藏字段（1是 0否）
	*/
	@Schema(description = "隐藏字段（1是 0否）")
	private Boolean isHide;
	/**
	* 覆盖字段（1是 0否）
	*/
	@Schema(description = "覆盖字段（1是 0否）")
	private Boolean isCover;
	/**
	* 查询方式（等于、不等于、大于、小于、范围）
	*/
	@Schema(description = "查询方式（等于、不等于、大于、小于、范围）")
	private String queryType;
	/**
	* 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
	*/
	@Schema(description = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
	private String htmlType;
	/**
	* 字典类型
	*/
	@Schema(description = "字典类型")
	private String dictType;
	/**
	* 显示顺序
	*/
	@Schema(description = "显示顺序")
	private Integer sort;

	public String readNameNoSuffix() {
		return StrUtil.isNotEmpty(this.getComment()) ? this.getComment().replaceAll("(?:\\（)[^\\(\\)]*(?:\\）)", StrUtil.EMPTY) : this.getComment();
	}

}
