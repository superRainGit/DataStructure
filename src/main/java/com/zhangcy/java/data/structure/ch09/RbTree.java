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
        RbNode<T> newNode = new RbNode<>(data, RbEnum.RED, null, null, null, null);
        // 查找当前新节点能插入的位置
        // 如果根节点为空 那么直接将新增节点置为父节点 同时需要进行变色 为了满足规则2
        if(ObjectUtil.isNull(this.root)) {
            newNode.rbEnum = RbEnum.BLACK;
            this.root = newNode;
        } else {
            // 当前正在遍历的节点 从根节点开始进行遍历
            RbNode<T> currentNode = this.root;
            // 遍历查找要插入的节点的位置
            while(true) {
                // 先查询当前节点是否是黑色节点 如果是再判断是不是有两个红色节点
                if(currentNode.isBlack() && currentNode.isPrefect()) {
                    // 如果子节点是红色的 那么先需要进行颜色的变换
                    if(currentNode.leftChild.isRed() && currentNode.rightChild.isRed()) {
                        currentNode.leftChild.rbEnum = RbEnum.BLACK;
                        currentNode.rightChild.rbEnum = RbEnum.BLACK;
                        // 需要判断当前节点是不是根节点 如果是根节点 那么根节点需要变成黑色
                        if(this.root != currentNode) {
                            currentNode.setRbEnum(RbEnum.RED);
                            // 变完颜色之前之后就需要调整数的结构
                            // 如果从上至下的直接父节点已经是红色，因为此次的变色出现红-红冲突
                            // 然后引导需要进行树的旋转 这也是两种情况
                            // 内联节点和外联节点
                            if(currentNode.parentNode.isRed()) {
                                // 优先处理所有的内联节点 将其转换为外联节点
                                // 下面仅处理外联节点即可
                                if((currentNode.lrEnum.ordinal() ^ currentNode.parentNode.lrEnum.ordinal()) == 1) {
                                    // 当知道异或的结果的时候 只要判断一边就可以
                                    // 不同于叶子节点的红-红冲突 在树内部因为变色造成的红-红冲突会出现子树的平移
                                    RbNode<T> parent = currentNode.parentNode;
                                    RbNode<T> grandParent = parent.parentNode;
                                    if(currentNode.isLeft()) {
                                        // 如果是左内联子树 那么需要进行树的右旋
                                        // 当前节点的右节点进行水平右移
                                        parent.leftChild = currentNode.rightChild;
                                        currentNode.rightChild.parentNode = parent;
                                        grandParent.rightChild = currentNode;
                                        currentNode.rightChild = parent;
                                        currentNode.lrEnum = LrEnum.RIGHT;
                                        parent.leftChild.lrEnum = LrEnum.LEFT;
                                    } else {
                                        // 如果是右内联子树 那么需要进行树的左旋
                                        // 当前节点的左节点进行水平左移
                                        parent.rightChild = currentNode.leftChild;
                                        currentNode.leftChild.parentNode = parent;
                                        grandParent.leftChild = currentNode;
                                        currentNode.leftChild = parent;
                                        currentNode.lrEnum = LrEnum.LEFT;
                                        parent.rightChild.lrEnum = LrEnum.RIGHT;
                                    }
                                    parent.parentNode = currentNode;
                                    currentNode.parentNode = grandParent;
                                    // 做完旋转之后 进行当前、父、祖父节点的调整
                                    currentNode = parent;
                                }
                                // 经过上述处理之后 只剩下了两种不同的外联节点
                                // 先处理完成  处理完成之后 因为树的结构有可能因为旋转发生了根本性的改变
                                // 父节点
                                RbNode<T> parent = currentNode.parentNode;
                                // 祖父节点
                                RbNode<T> grandParent = parent.parentNode;
                                // 祖父节点的父节点
                                RbNode<T> grandParentParent = grandParent.parentNode;
                                // 左向外联链接
                                if(currentNode.isLeft()) {
                                    grandParent.leftChild = parent.rightChild;
                                    grandParent.leftChild.parentNode = grandParent;
                                    parent.rightChild = grandParent;
                                    grandParent.lrEnum = LrEnum.RIGHT;
                                } else {
                                    grandParent.rightChild = parent.leftChild;
                                    grandParent.rightChild.parentNode = grandParent;
                                    parent.leftChild = grandParent;
                                    grandParent.lrEnum = LrEnum.LEFT;
                                }
                                grandParent.parentNode = parent;
                                // 父节点要变成黑色
                                parent.rbEnum = RbEnum.BLACK;
                                // 祖父节点变红
                                grandParent.rbEnum = RbEnum.RED;
                                // 最后处理祖父节点的父节点
                                if(ObjectUtil.isNotNull(grandParentParent)) {
                                    if(grandParentParent.leftChild == grandParent) {
                                        grandParentParent.leftChild = parent;
                                        parent.lrEnum = LrEnum.LEFT;
                                    } else {
                                        grandParentParent.rightChild = parent;
                                        parent.lrEnum = LrEnum.RIGHT;
                                    }
                                } else {
                                   this.root = parent;
                                   parent.lrEnum = null;
                                }
                                parent.parentNode = grandParentParent;
                                // 直接从目前发现的父亲节点进行继续的查找
                                currentNode = parent;
                                continue;
                            }
                        }
                    }
                }
                // 如果当前遍历的节点比要插入的节点大
                // 不如反方向的比较好理解 因为那样顺着人的意思
                // 即比较当前要插入的节点和之前的节点的对比
                if(data.compareTo(currentNode.data) > 0) {
                    // 如果当前节点有右子节点 那么往右
                    if(ObjectUtil.isNotNull(currentNode.rightChild)) {
                        // 如果向右查找 那么表明父节点是祖父节点的右节点
                        currentNode = currentNode.rightChild;
                    } else {
                        // 否则的话已经找到了结尾 应该在当前节点的右边插入当前元素
                        // 其实可以在遍历结束之时直接插入元素
                        currentNode.rightChild = newNode;
                        newNode.lrEnum = LrEnum.RIGHT;
                        newNode.parentNode = currentNode;
                        break;
                    }
                } else if(data.compareTo(currentNode.data) < 0) {
                    // 如果当前节点有左子节点 那么往左
                    if(ObjectUtil.isNotNull(currentNode.leftChild)) {
                        // 如果向左查找 那么表明父节点是祖父节点的左节点
                        currentNode = currentNode.leftChild;
                    } else {
                        // 否则的话已经找到了结尾 应该在当前节点的左边插入当前元素
                        // 其实可以在遍历结束之时直接插入元素
                        currentNode.leftChild = newNode;
                        newNode.lrEnum = LrEnum.LEFT;
                        newNode.parentNode = currentNode;
                        break;
                    }
                }
            }
            // 判断一下是否有违背红黑树的存在 如果有就需要做旋转
            // 判断一下父节点和子节点是否冲突
            if(currentNode.isRed()) {
                // 因为是直接在叶子节点完成的插入 那么就会有四种情况 俩种大情况
                // 俩种大情况为内联节点或者外联节点
                // 我们先优先处理内联节点变为外联节点 那么都变为外联节点的是就可以直接一起处理了
                // 先处理内联节点
                // 找到祖父节点
                RbNode<T> parentNode = currentNode.parentNode;
                if((newNode.lrEnum.ordinal() ^ currentNode.lrEnum.ordinal()) == 1) {
                    // 当异或运算为1的时候 其实判断一边就已经知道了另外的一边 没必要进行两次不同的判断
                    // 如果 右 -> 左 的外联节点 就需要进行子树的右旋
                    if(newNode.isLeft()) {
                        parentNode.rightChild = newNode;
                        newNode.lrEnum = LrEnum.RIGHT;
                        newNode.rightChild = currentNode;
                        currentNode.leftChild = null;
                    } else {
                        parentNode.leftChild = newNode;
                        newNode.lrEnum = LrEnum.LEFT;
                        newNode.leftChild = currentNode;
                        currentNode.rightChild = null;
                    }
                    currentNode.parentNode = newNode;
                    newNode.parentNode = parentNode;
                    // 做完内联向外联的变换之后 需要跳转current和new的指向 方便下面做处理
                    newNode = currentNode;
                    currentNode = newNode.parentNode;
                }
                // 目前已经处理了所有的内联节点 现在全部的变成了外联节点
                // 找到祖父的父节点
                RbNode<T> grandParent = parentNode.parentNode;
                // 如何处理外联结构的节点 先变色 后旋转
                // 将新节点的父节点变为黑色 同时将祖父节点变为红色
                currentNode.rbEnum = RbEnum.BLACK;
                parentNode.rbEnum = RbEnum.RED;
                // 判断一下是左旋还是右旋
                if(newNode.isLeft()) {
                    // 如果一直是左方向节点 那么进行右旋
                    currentNode.rightChild = parentNode;
                    parentNode.lrEnum = LrEnum.RIGHT;
                    parentNode.leftChild = null;
                } else {
                    // 否则左旋
                    currentNode.leftChild = parentNode;
                    parentNode.lrEnum = LrEnum.LEFT;
                    parentNode.rightChild = null;
                }
                parentNode.parentNode = currentNode;
                // 处理祖父节点
                if(ObjectUtil.isNotNull(grandParent)) {
                    if(grandParent.leftChild == parentNode) {
                        // 左子树
                        currentNode.lrEnum = LrEnum.LEFT;
                        grandParent.leftChild = currentNode;
                    } else {
                        // 右子树
                        parentNode.lrEnum = LrEnum.RIGHT;
                        grandParent.rightChild = currentNode;
                    }
                } else {
                    // 如果为空 那么就是根节点
                    this.root = currentNode;
                    currentNode.lrEnum = null;
                }
                currentNode.parentNode = grandParent;
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
