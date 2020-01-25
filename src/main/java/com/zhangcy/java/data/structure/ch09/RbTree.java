package com.zhangcy.java.data.structure.ch09;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 红黑树
 * 红黑树满足的基本需求如下
 * 1、红黑树的节点必要要么是红色的要么是黑色
 * 2、红黑树的根节点必须是黑色的
 * 3、红黑树的红色节点的子节点必须是黑色的节点 反之未必
 * 4、红黑树的从根节点到叶子节点或者空子节点的黑色节点的深度必须一致
 * @author zhangcy
 */
@Slf4j
public class RbTree<T extends Comparable<T>> {

    /**
     * 红黑树的根节点
     */
    private RbNode<T> root;

    /**
     * 在红黑树中插入一个新的节点
     * 在红黑树插入过程中 有可能涉及到树的节点的旋转和树的节点的变色
     * 新插入的节点的颜色本身就是红色的
     * 为啥选红色嘞？因为红色是最少能造出红黑树出问题的选择
     * 有什么处理的方式呢
     * 1、首先插入红黑树本身就是一个搜索二叉树
     * 2、在插入的过程中 如果当前遍历的是黑色的节点 且有两个红色的节点 那么需要对这个进行变色处理
     * 将红色节点变为黑色节点 黑色节点变为红色节点
     * 第二步首先不会造成黑色节点的深度发生变化
     * 有可能会造成变色之后出现红-红的链接
     * 3、在找到的位置进行节点的插入
     * 如果插入的节点的父节点为黑色 则什么也不需要做
     * 如果插入节点的父节点为红色 且当前节点、父节点、祖父节点构成了外连节点 那么需要一次旋转
     * 如果插入节点的父节点为红色 且当前节点、父节点、祖父节点构成了内连节点 那么需要两次旋转
     * 4、在从上到下遍历的过程中进行树内的旋转
     * 这个一般是解决第二步进行变色之后的处理
     * 他的处理的方式也是旋转 同第三步
     */
    public void insert(T data) {
        // 先创建新的节点
        RbNode<T> newNode = new RbNode<>(data, RbEnum.RED, null, null, null);
        // 查找当前新节点能插入的位置
        // 如果根节点为空 那么直接将新增节点置为父节点 同时需要进行变色 为了满足规则2
        if(ObjectUtil.isNull(this.root)) {
            newNode.setRbEnum(RbEnum.BLACK);
            this.root = newNode;
        } else {
            // 当前正在遍历的节点
            RbNode<T> currentNode = this.root;
            // 父节点
            RbNode<T> parentNode = null;
            // 祖父节点
            RbNode<T> grandParent = null;
            // 当前节点[要插入的节点]和父节点关系
            int lrCp = -1;
            // 父节点和祖父节点的关系
            int lrPg = -1;
            while(true) {
                // 先查询当前节点是否是黑色节点 如果是再判断是不是有两个红色节点
                if(currentNode.isBlack() && currentNode.isPrefect()) {
                    // 如果子节点是红色的 那么先需要进行颜色的变换
                    if(currentNode.getLeftChild().isRed()) {
                        currentNode.getLeftChild().setRbEnum(RbEnum.BLACK);
                        currentNode.getRightChild().setRbEnum(RbEnum.BLACK);
//                        // 需要判断当前节点是不是根节点 如果是根节点 那么根节点需要变成黑色
//                        if(this.root != currentNode) {
//                            currentNode.setRbEnum(RbEnum.RED);
//                            // 需要判断当前节点的父节点是不是和当前节点的颜色冲突 即将当前节点变为红色之后是否出现了红-红双节点
//                            RbNode<T> parentNode = currentNode.getParentNode();
//                            if(parentNode.isRed()) {
//                                // 如果出现了红-红双节点 那么就需要进行旋转
//                                // 如何进行旋转? 如果是外联节点 需要进行一次旋转
//                                // 如果是内联节点 需要进行两次旋转
//                                // 如果是外联节点
//                                if(((parentNode.getLeftChild() == currentNode) && parentNode.getParentNode().getLeftChild() == parentNode)
//                                        || ((parentNode.getRightChild() == currentNode) && (parentNode.getParentNode().getRightChild() == parentNode))) {
//                                    // 需要先进行变色
//                                    // 先变祖父节点的颜色
//                                    parentNode.getParentNode().setRbEnum(RbEnum.RED);
//                                    parentNode.setRbEnum(RbEnum.BLACK);
//                                } else {
//
//                                }
//                            }
//                        }
                    }
                }
                // 如果当前遍历的节点比要插入的节点大
                // 不如反方向的比较好理解 因为那样顺着人的意思
                // 即比较当前要插入的节点和之前的节点的对比
                if(data.compareTo(currentNode.getData()) > 0) {
                    // 如果当前节点有右子节点 那么往右
                    if(ObjectUtil.isNotNull(currentNode.getRightChild())) {
                        // 如果向右查找 那么表明父节点是祖父节点的右节点
                        grandParent = currentNode;
                        currentNode = currentNode.getRightChild();
                        lrPg = 0;
                    } else {
                        // 否则的话已经找到了结尾 应该在当前节点的右边插入当前元素
                        lrCp = 0;
                        break;
                    }
                } else if(data.compareTo(currentNode.getData()) < 0) {
                    // 如果当前节点有左子节点 那么往左
                    if(ObjectUtil.isNotNull(currentNode.getLeftChild())) {
                        // 如果向左查找 那么表明父节点是祖父节点的左节点
                        grandParent = currentNode;
                        currentNode = currentNode.getLeftChild();
                        lrPg = 1;
                    } else {
                        // 否则的话已经找到了结尾 应该在当前节点的左边插入当前元素
                        lrCp = 1;
                        break;
                    }
                }
            }
            parentNode = currentNode;
            // 当前节点就是最后找到的要插入的节点的位置
            newNode.setParentNode(parentNode);
            if(lrCp == 1) {
                parentNode.setLeftChild(newNode);
            } else {
                parentNode.setRightChild(newNode);
            }
            // 判断一下是否有违背红黑树的存在 如果有就需要做旋转
            // 判断一下父节点和子节点是否冲突
            if(parentNode.isRed()) {
                // 因为是直接在叶子节点完成的插入 那么就会有四种情况 俩种大情况
                // 俩种大情况为内联节点或者外联节点
                // 我们先优先处理内联节点变为外联节点 那么都变为外联节点的是就可以直接一起处理了
                // 先处理内联节点
                if((lrCp ^ lrPg) == 1) {
                    // 需要进行右旋
                    if(lrPg == 1) {
                        grandParent.setLeftChild(newNode);
                    } else {
                        grandParent.setRightChild(newNode);
                    }
                    if(lrCp == 1) {
                        newNode.setRightChild(parentNode);
                    } else {
                        newNode.setLeftChild(parentNode);
                    }
                    newNode.setParentNode(grandParent);
                    parentNode.setLeftChild(null);
                    parentNode.setRightChild(null);
                    parentNode.setParentNode(newNode);
                    lrCp = lrCp ^ 1;
                    newNode = parentNode;
                    parentNode = newNode.getParentNode();
                }
                // 目前已经处理了所有的内联节点 现在全部的变成了外联节点
                // 如何处理外联节点?
                // 将父节点变为黑色 同时将祖父节点变为红色
                parentNode.setRbEnum(RbEnum.BLACK);
                grandParent.setRbEnum(RbEnum.RED);
                RbNode<T> grandParentParent = grandParent.getParentNode();
                grandParent.setLeftChild(null);
                grandParent.setRightChild(null);
                grandParent.setParentNode(parentNode);
                if(lrCp == 1) {
                    // 左内联节点
                    parentNode.setRightChild(grandParent);
                } else {
                    parentNode.setLeftChild(grandParent);
                }
                // 处理祖父节点
                if(ObjectUtil.isNotNull(grandParentParent)) {
                    if(grandParentParent.getLeftChild() == grandParent) {
                        // 左子树
                        grandParentParent.setLeftChild(parentNode);
                    } else {
                        // 右子树
                        grandParentParent.setRightChild(parentNode);
                    }
                } else {
                    // 如果为空 那么就是根节点
                    this.root = parentNode;
                    parentNode.setParentNode(null);
                }
            }
        }
    }

    /**
     * 展示树的结构 先序遍历
     */
    public void display() {
        recursivePreOrder(this.root);
    }

    /**
     * 地递归的进行先序遍历
     */
    private void recursivePreOrder(RbNode<T> rbNode) {
        if(ObjectUtil.isNotNull(rbNode)) {
            log.info("current Node = {}", rbNode);
            recursivePreOrder(rbNode.getLeftChild());
            recursivePreOrder(rbNode.getRightChild());
        }
    }
}
