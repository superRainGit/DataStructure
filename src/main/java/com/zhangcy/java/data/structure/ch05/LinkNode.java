package com.zhangcy.java.data.structure.ch05;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 链结点类
 * @author zhangcy
 */
@Getter
@Setter
@AllArgsConstructor
public class LinkNode<T> {

    /**
     * 存储当前链结点的数据
     */
    private T data;

    /**
     * 存储指向下一个链结点的指针
     * 该字段中存储的数据类型其实是一个LinkNode对象的地址
     */
    private LinkNode<T> next;
}
