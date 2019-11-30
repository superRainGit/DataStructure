package com.zhangcy.java.data.structure.ch05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 双向队列的节点
 * @author zhangcy
 */
@Getter
@Setter
@AllArgsConstructor
public class DoubleLinkNode<T> {

    /**
     * 存储当前链结点的数据
     */
    private T data;

    /**
     * 存储指向下一个链结点的指针
     * 该字段中存储的数据类型其实是一个LinkNode对象的地址
     */
    private DoubleLinkNode<T> next;

    /**
     * 当前节点的前节点
     */
    private DoubleLinkNode<T> previous;
}
