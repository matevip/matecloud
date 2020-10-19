package vip.mate.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vip.mate.core.web.tree.INode;
import vip.mate.system.entity.SysMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单数据传输对象
 *
 * @author xuzhanfu
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuDTO extends SysMenu implements INode {

	private static final long serialVersionUID = -7053157666510171528L;

	private String label;

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
