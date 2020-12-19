package vip.mate.system.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import vip.mate.core.web.tree.INode;
import vip.mate.system.entity.MenuMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单视图对象
 *
 * @author xuzhanfu
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuVO implements INode {

	private static final long serialVersionUID = -8036122418979736148L;

	/**
	 * 菜单ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键")
	private Long id;

	/**
	 * 菜单标题
	 */
	private String name;

	/**
	 * 菜单权限
	 */
	private String permission;

	/**
	 * 路径
	 */
	private String path;

	/**
	 * 父菜单ID
	 */
	private Long parentId;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 菜单类型
	 */
	private String type;

	/**
	 * 排序值
	 */
	private Integer sort;

	private MenuMeta meta;

	private String component;

	private Boolean hidden;

	private String redirect;

	private Boolean alwaysShow;

	private Boolean target;

	private String typeName;

	/**
	 * 子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<INode> children;

	/**
	 * 是否有子孙节点
	 */
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Boolean hasChildren;

	@Override
	public List<INode> getChildren() {
		if (this.children == null) {
			this.children = new ArrayList<>();
		}
		return this.children;
	}

}
