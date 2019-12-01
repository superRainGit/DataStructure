package com.zhangcy.java.data.structure.ch05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 二维链表的链结点
 * @author zhangcy
 */
@Getter @Setter
@AllArgsConstructor
public class TwoDimensionalNode<T> {

    /**
     * 数据域
     */
    private T data;

    /**
     * 右面的结点
     */
    private TwoDimensionalNode<T> right;

    /**
     * 下面的结点
     */
    private TwoDimensionalNode<T> down;
}
