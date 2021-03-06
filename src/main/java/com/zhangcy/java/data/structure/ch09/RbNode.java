package com.zhangcy.java.data.structure.ch09;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 红黑树的节点
 * @author zhangcy
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RbNode<T extends Comparable<T>> {

    /**
     * 节点中的数据
     */
    protected T data;

    /**
     * 表明红黑节点 true 表明是黑节点 false 表明是红节点
     */
    protected RbEnum rbEnum;

    /**
     * 当前节点是父节点的什么节点
     */
    protected LrEnum lrEnum;

    /**
     * 左子节点
     */
    protected RbNode<T> leftChild;

    /**
     * 右子节点
     */
    protected RbNode<T> rightChild;

    /**
     * 父亲节点
     */
    protected RbNode<T> parentNode;

    /**
     * 当前节点是不是红色节点
     */
    public boolean isRed() {
        return this.rbEnum == RbEnum.RED;
    }

    /**
     * 当前节点是不是黑色节点
     */
    public boolean isBlack() {
        return this.rbEnum == RbEnum.BLACK;
    }

    /**
     * 判断当前节点是不是父节点的左子节点
     */
    public boolean isLeft() {
        return ObjectUtil.isNotNull(this.lrEnum) && this.lrEnum == LrEnum.LEFT;
    }

    /**
     * 判断当前节点是不是父节点的右子节点
     */
    public boolean isRight() {
        return ObjectUtil.isNotNull(this.lrEnum) && this.lrEnum == LrEnum.RIGHT;
    }

    /**
     * 当前节点是否是完美节点
     * 完美节点：有左右两个子节点
     */
    public boolean isPrefect() {
        return ObjectUtil.isNotNull(this.leftChild) && ObjectUtil.isNotNull(this.rightChild);
    }

    /**
     * 打印当前节点的信息
     */
    @Override
    public String toString() {
        return "RbNode{" +
                "data=" + data +
                ", rbEnum=" + rbEnum +
                ", lrEnum=" + lrEnum +
                ", leftChild=" + (ObjectUtil.isNotNull(this.getLeftChild()) ? leftChild.getData() : "null") +
                ", rightChild=" + (ObjectUtil.isNotNull(this.getRightChild()) ? rightChild.getData() : "null") +
                ", parentNode is " + (ObjectUtil.isNotNull(this.getParentNode()) ? this.getParentNode().getData() : "null") +
                '}';
    }
}
