package com.zhangcy.java.data.structure.ch08;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 树的节点
 * @author zhangcy
 */
@Getter @Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode<T extends Comparable<T>> {

    /**
     * 树中的节点数据
     */
    private T data;

    /**
     * 二叉树的左子节点
     */
    private TreeNode<T> leftChild;

    /**
     * 二叉树的右子节点
     */
    private TreeNode<T> rightChild;

    /**
     * 当前节点的父节点
     */
    private TreeNode<T> parent;

    /**
     * 展示树中的节点
     * 目前仅打印节点中的存储的数据信息
     */
    public void display() {
        log.info("current tree node data {}", data);
    }

    /**
     * 判断一个节点是不是根节点
     */
    public boolean isRoot() {
        return ObjectUtil.isNull(this.parent);
    }

    /**
     * 判断一个节点是否是叶子节点
     */
    public boolean isLeaf() {
        return ObjectUtil.isNull(this.leftChild) && ObjectUtil.isNull(this.rightChild);
    }
}
