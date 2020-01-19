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
            // 默认往右节点插入数据
            int lr = 0;
            while(true) {
                // 先查询当前节点是否是黑色节点 如果是再判断是不是有两个红色节点
                if(currentNode.isBlack() && currentNode.isPrefect()) {
                    // 如果子节点是红色的 那么先需要进行颜色的变换
                    if(currentNode.getLeftChild().isRed()) {
                        currentNode.getLeftChild().setRbEnum(RbEnum.BLACK);
                        currentNode.getRightChild().setRbEnum(RbEnum.BLACK);
                        // 需要判断当前节点是不是根节点 如果是根节点 那么根节点需要变成黑色
                        if(this.root != currentNode) {
                            currentNode.setRbEnum(RbEnum.RED);
                            // 需要判断当前节点的父节点是不是和当前节点的颜色冲突 即将当前节点变为红色之后是否出现了红-红双节点
                            RbNode<T> parentNode = currentNode.getParentNode();
                            if(parentNode.isRed()) {
                                // 如果出现了红-红双节点 那么就需要进行旋转
                                // 如何进行旋转? 如果是外联节点 需要进行一次旋转
                                // 如果是内联节点 需要进行两次旋转
                                // 如果是外联节点
                                if(((parentNode.getLeftChild() == currentNode) && parentNode.getParentNode().getLeftChild() == parentNode)
                                        || ((parentNode.getRightChild() == currentNode) && (parentNode.getParentNode().getRightChild() == parentNode))) {
                                    // 需要先进行变色
                                    // 先变祖父节点的颜色
                                    parentNode.getParentNode().setRbEnum(RbEnum.RED);
                                    parentNode.setRbEnum(RbEnum.BLACK);
                                } else {

                                }
                            }
                        }
                    }
                }
                // 如果当前遍历的节点比要插入的节点大
                if(currentNode.getData().compareTo(data) > 0) {
                    lr = 1;
                    if(ObjectUtil.isNotNull(currentNode.getLeftChild())) {
                        currentNode = currentNode.getLeftChild();
                    } else {
                        break;
                    }
                } else if(currentNode.getData().compareTo(data) < 0) {
                    lr = 0;
                    if(ObjectUtil.isNotNull(currentNode.getRightChild())) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        break;
                    }
                }
            }
            // 当前节点就是最后找到的要插入的节点的位置
            newNode.setParentNode(currentNode);
            if(lr == 1) {
                currentNode.setLeftChild(newNode);
            } else {
                currentNode.setRightChild(newNode);
            }
            // 判断一下父节点和子节点是否冲突
            if(currentNode.isRed()) {
                // 有冲突的话需要进行旋转
                // 判断是内联节点还是外联节点
                if(lr == 1 && currentNode.getParentNode().getLeftChild() == currentNode) {
                    // 左外联节点 需要进行一次右旋转
                    currentNode.setRbEnum(RbEnum.BLACK);
                    // 获取父节点
                    RbNode<T> parentNode = currentNode.getParentNode();
                    // 取祖父节点
                    RbNode<T> grandParent = parentNode.getParentNode();
                    parentNode.setRbEnum(RbEnum.RED);
                    parentNode.setLeftChild(null);
                    parentNode.setRightChild(null);
                    parentNode.setParentNode(currentNode);
                    currentNode.setRightChild(parentNode);
                    // 如果父节点是根节点
                    if(this.root == parentNode) {
                        // 那么进行右旋之后根节点就会发生变动
                        currentNode.setParentNode(null);
                        this.root = currentNode;
                    } else {
                        // 如果不是 直接进行右旋 需要修改原祖父节点的子节点
                        if(grandParent.getLeftChild() == parentNode) {
                            grandParent.setLeftChild(currentNode);
                        } else {
                            grandParent.setRightChild(currentNode);
                        }
                    }
                } else if(lr == 0 && currentNode.getParentNode().getRightChild() == currentNode) {
                    // 右外联节点 需要进行一次左旋转
                    currentNode.setRbEnum(RbEnum.BLACK);
                    // 获取父节点
                    RbNode<T> parentNode = currentNode.getParentNode();
                    // 取祖父节点
                    RbNode<T> grandParent = parentNode.getParentNode();
                    parentNode.setRbEnum(RbEnum.RED);
                    parentNode.setLeftChild(null);
                    parentNode.setRightChild(null);
                    parentNode.setParentNode(currentNode);
                    currentNode.setLeftChild(parentNode);
                    // 如果父节点是根节点
                    if(this.root == parentNode) {
                        // 那么进行左旋之后根节点就会发生变动
                        currentNode.setParentNode(null);
                        this.root = currentNode;
                    } else {
                        // 如果不是 直接进行左旋 需要修改原祖父节点的子节点
                        if(grandParent.getLeftChild() == parentNode) {
                            grandParent.setLeftChild(currentNode);
                        } else {
                            grandParent.setRightChild(currentNode);
                        }
                        currentNode.setParentNode(grandParent);
                    }
                } else if(lr == 1 && currentNode.getParentNode().getRightChild() == currentNode) {
                    // 左内联节点 需要两次旋转
                    // 1、先变色
                    newNode.setRbEnum(RbEnum.BLACK);
                    // 获取父亲节点和祖父节点
                    RbNode<T> parent = currentNode.getParentNode();
                    // 祖父节点
                    RbNode<T> grandParent = parent.getParentNode();
                    // 2、首先需要内节点右旋 然后整体左旋
                    // 先右旋
                    parent.setRbEnum(RbEnum.RED);
                    newNode.setRightChild(currentNode);
                    newNode.setLeftChild(parent);
                    parent.setParentNode(newNode);
                    parent.setLeftChild(null);
                    parent.setRightChild(null);
                    currentNode.setLeftChild(null);
                    currentNode.setRightChild(null);
                    currentNode.setParentNode(newNode);
                    // 然后整体左旋
                    // 如果是根节点
                    if(parent == this.root) {
                        this.root = newNode;
                        newNode.setParentNode(null);
                    } else {
                        newNode.setParentNode(grandParent);
                        if(grandParent.getLeftChild() == parent) {
                            grandParent.setLeftChild(newNode);
                        } else {
                            grandParent.setRightChild(newNode);
                        }
                    }
                } else {
                    // 右内联节点 需要两次旋转
                    // 1、先变色
                    newNode.setRbEnum(RbEnum.BLACK);
                    // 2、先节点右旋 再整棵树左旋
                    // 取父亲节点和祖父节点
                    RbNode<T> parent = currentNode.getParentNode();
                    parent.setRbEnum(RbEnum.RED);
                    RbNode<T> grandParent = parent.getParentNode();
                    // 先右旋
                    currentNode.setLeftChild(null);
                    currentNode.setRightChild(null);
                    currentNode.setParentNode(newNode);
                    newNode.setLeftChild(currentNode);
                    newNode.setRightChild(parent);
                    parent.setParentNode(newNode);
                    parent.setLeftChild(null);
                    parent.setRightChild(null);
                    // 再整体左旋
                    if(this.root == parent) {
                        newNode.setParentNode(null);
                        this.root = newNode;
                    } else {
                        if(grandParent.getLeftChild() == parent) {
                            grandParent.setLeftChild(newNode);
                        } else {
                            grandParent.setRightChild(newNode);
                        }
                        newNode.setParentNode(grandParent);
                    }
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