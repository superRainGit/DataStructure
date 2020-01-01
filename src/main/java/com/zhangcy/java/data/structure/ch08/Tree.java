package com.zhangcy.java.data.structure.ch08;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 树本身的实例[目前在写的是一个二叉搜索树]
 * @author zhangcy
 */
@Slf4j
public class Tree<T extends Comparable<T>> {

    /**
     * 树的根节点
     * 因为树是打算通过根节点进行数据的完全访问
     */
    private TreeNode<T> root;

    /**
     * 查询节点
     * 因为是个二插搜索树 默认的就是左边的数据比父节点小  右边的数据比父节点大
     */
    public void find(T key) {
        // 先找到父节点
        TreeNode<T> currNode = root;
        // 当前节点的数据不和想要找的数据相等并且不是叶子节点
        while(ObjectUtil.isNotNull(currNode) && currNode.getData().compareTo(key) != 0 && !currNode.isLeaf()) {
            // 如果当前要找的数据比节点数据要小
            if(currNode.getData().compareTo(key) > 0) {
                // 向左子树寻找
                currNode = currNode.getLeftChild();
            } else {
                // 向右子树寻找
                currNode = currNode.getRightChild();
            }
        }
        if(ObjectUtil.isNotNull(currNode)) {
            currNode.display();
        } else {
            log.error("find nothing");
        }
    }

    /**
     * 向目前已有的树中插入数据
     * 我们定义一下：当数据有重复的时候 直接重复的节点按照比当前元素小处理
     */
    public void insert(T data) {
        // 先找到父节点
        TreeNode<T> currNode = this.root;
        TreeNode<T> parentNode = null;
        // 应该插入到当前树的什么节点上
        byte lr = 0;
        // 找到插入该节点的位置
        // 什么时候插入呢 先匹配的找到它应该在的位置
        while(ObjectUtil.isNotNull(currNode)) {
            T currData = currNode.getData();
            if(currData.compareTo(data) >= 0) {
                // 要插入的元素比较小
                parentNode = currNode;
                currNode = currNode.getLeftChild();
            } else {
                // 要插入的元素比较大
                parentNode = currNode;
                currNode = currNode.getRightChild();
                lr = 1;
            }
        }
        TreeNode<T> newTreeNode = new TreeNode<>(data, null, null, parentNode);
        if(ObjectUtil.isNotNull(parentNode)) {
            // 如果不为空 那么表明以及找到了数据需要存储的位置
            // 判断一下应该放左边还是放右边
            // 应该放左边
            if(lr == 0) {
                parentNode.setLeftChild(newTreeNode);
            } else {
                parentNode.setRightChild(newTreeNode);
            }
        } else {
            // 如果parentNode是null 有两种可能 数据一样或者根节点也没有
            // 先暂时考虑是根节点不存在
            this.root = newTreeNode;
        }
    }

    /**
     * 树的中序遍历
     * 访问当前节点的左子树
     * 访问当前节点
     * 访问当前节点的右子树
     */
    public void inOrder() {
        recursiveInOrder(this.root);
    }

    /**
     * 递归的进行数的中序遍历
     * 什么时候递归终止呢?
     * 当遍历的节点为null时表明已经到头了
     */
    private void recursiveInOrder(TreeNode<T> currNode) {
        if(ObjectUtil.isNotNull(currNode)) {
            // 先遍历当前节点的左子树
            recursiveInOrder(currNode.getLeftChild());
            // 访问当前节点
            currNode.display();
            // 再遍历当前节点的右子树
            recursiveInOrder(currNode.getRightChild());
        }
    }

    /**
     * 删除指定的元素的节点
     */
    public void delete(T key) {

    }
}
