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
 * 附件表实体类
 *
 * @author pangu
 * @since 2020-08-09
 */
@Data
@TableName("mate_sys_attachment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SysAttachment对象", description = "附件表")
public class SysAttachment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 存储ID
	*/
	@ApiModelProperty(value = "存储ID")
	private Long storageId;
	/**
	* 组ID
	*/
	@ApiModelProperty(value = "组ID")
	private Integer attachmentGroupId;
	/**
	* 文件名称
	*/
	@ApiModelProperty(value = "文件名称")
	private String name;
	/**
	* 文件大小
	*/
	@ApiModelProperty(value = "文件大小")
	private long size;
	/**
	* 文件地址
	*/
	@ApiModelProperty(value = "文件地址")
	private String url;

	/**
	 * 上传文件名
	 */
	@ApiModelProperty(value = "上传文件名")
	private String fileName;

	/**
	* 缩略图地址
	*/
	@ApiModelProperty(value = "缩略图地址")
	private String thumbUrl;
	/**
	* 类型
	*/
	@ApiModelProperty(value = "类型")
	private Integer type;
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
	/**
	* 删除标识
	*/
	@ApiModelProperty(value = "删除标识")
	private String isDeleted;
	/**
	* 是否加入回收站 0.否|1.是
	*/
	@ApiModelProperty(value = "是否加入回收站 0.否|1.是")
	private Boolean isRecycle;


}
