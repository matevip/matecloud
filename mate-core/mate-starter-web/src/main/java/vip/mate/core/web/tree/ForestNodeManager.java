package vip.mate.core.web.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 森林管理类
 * @param <T>
 * @author pangu
 * @link https://blog.csdn.net/u014424628/article/details/51765394?utm_source=blogxgwz2
 */
public class ForestNodeManager<T extends INode> {

    /**
     * 森林的所有节点
     */
    private List<T> list;

    /**
     * 森林的父节点ID
     */
    private List<Long> parentIds = new ArrayList<>();

    public ForestNodeManager(List<T> items) {
        list = items;
    }

    /**
     * 根据节点ID获取一个节点
     *
     * @param id 节点ID
     * @return 对应的节点对象
     */
    public INode getTreeNodeAT(Long id) {
        for (INode forestNode : list) {
            if (forestNode.getId().longValue() == id.longValue()) {
                return forestNode;
            }
        }
        return null;
    }

    /**
     * 增加父节点ID
     *
     * @param parentId
     */
    public void addParentId(Long parentId) {
        parentIds.add(parentId);
    }

    /**
     * 获取树的根节点(一个森林对应多颗树)
     *
     * @return 树的根节点集合
     */
    public List<T> getRoot() {
        List<T> roots = new ArrayList<>();
        for (T forestNode : list) {
            if (forestNode.getParentId() == 0 || parentIds.contains(forestNode.getId())) {
                roots.add(forestNode);
            }
        }
        return roots;
    }
}
