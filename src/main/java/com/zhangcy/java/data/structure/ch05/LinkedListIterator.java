package com.zhangcy.java.data.structure.ch05;

/**
 * 链表的迭代器
 * @author zhangcy
 */
public class LinkedListIterator<T> {

    /**
     * 源链表
     */
    private LinkedListX<T> parentList;

    /**
     * 指向链表的当前链结点
     */
    private LinkNode<T> current;

    /**
     * 指向链表的当前节点的前一个节点
     */
    private LinkNode<T> previous;

    /**
     * 初始化迭代器
     */
    public LinkedListIterator(LinkedListX<T> parentList) {
        this.parentList = parentList;
        this.current = parentList.getFirst();
        this.previous = null;
    }

    /**
     * 将迭代器进行重置
     */
    public void reset() {
        this.current = parentList.getFirst();
        this.previous = null;
    }

    /**
     * 得到下一个节点
     */
    public LinkNode<T> next() {
        this.current = this.current.getNext();
        return this.current;
    }

    /**
     * 获取当前节点中的元素
     */
    public T get() {
        return this.current.getData();
    }

    /**
     * 是否到达迭代器的末尾
     */
    public boolean atEnd() {
        return this.current.getNext() == null;
    }

    /**
     * 在迭代器的后面添加一个元素
     */
    public void insertAfter(T data) {
        LinkNode<T> node = new LinkNode<>(data, null);
        node.setNext(this.current.getNext());
        this.current.setNext(node);
        this.previous = this.current;
        this.current = this.current.getNext();
    }

    /**
     * 在迭代器的前面添加一个元素
     */
    public void insertBefore(T data) {
        LinkNode<T> node = new LinkNode<>(data, null);
        node.setNext(this.current);
        reset();
    }

    /**
     * 删除迭代器指向的当前节点
     */
    public T deleteCurrent() {
        LinkNode<T> current = this.current;
        this.current = this.current.getNext();
        this.previous.setNext(this.current);
        return current.getData();
    }
}
