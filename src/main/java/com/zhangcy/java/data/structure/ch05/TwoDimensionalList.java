package com.zhangcy.java.data.structure.ch05;

/**
 * 二维链表
 * ?? 怎么维护数据 怎么维护初始值
 * @author zhangcy
 */
public class TwoDimensionalList<T> {

    /**
     * 二维链表的头结点
     */
    private TwoDimensionalNode<T> head;

    /**
     * 行数
     */
    private int rows;

    /**
     * 列数
     */
    private int lines;

    /**
     * 构造器构造一个指定行和列的二维链表
     */
    public TwoDimensionalList(int rows, int lines) {
        this.rows = rows;
        this.lines = lines;
        this.head = null;
    }

    /**
     * 初始化指定行数和列数的二维链表
     */
    private void initSpecified() {

    }
}
