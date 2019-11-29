package com.zhangcy.java.data.structure.ch05;

/**
 * 双端链表
 * 即头结点持有第一个链接点和最后一个链结点
 * @author zhangcy
 */
public class FirstLastLinkedList<T> {

    /**
     * 指向头结点的指针
     */
    private LinkNode<T> head;

    /**
     * 指向尾结点的指针
     */
    private LinkNode<T> tail;

    /**
     * 双端链表的构造器
     */
    public FirstLastLinkedList() {
        this.head = new LinkNode<>(null, null);
        this.tail = new LinkNode<>(null, null);
    }

    /**
     * 在头部插入数据
     */
    public void insertHead(T data) {
        LinkNode<T> linkNode = new LinkNode<>(data, this.head.getNext());
        // 如果是首次插入元素 那么需要将尾指针进行赋值
        if(this.head.getNext() == null) {
            this.tail.setNext(linkNode);
        }
        this.head.setNext(linkNode);
    }

    /**
     * 在尾部插入数据
     */
    public void insertLast(T data) {
        LinkNode<T> linkNode = new LinkNode<>(data, null);
        // 如果头结点为空
        if(this.head.getNext() == null) {
            this.head.setNext(linkNode);
        }
        if(this.tail.getNext() != null) {
            LinkNode<T> lastNode = this.tail.getNext();
            lastNode.setNext(linkNode);
        }
        this.tail.setNext(linkNode);
    }

    /**
     * 删除头结点
     */
    public T deleteHead() {
        if(this.head.getNext() == null) {
            throw new IllegalArgumentException("linked list is empty");
        }
        // 取出头结点数据
        LinkNode<T> first = this.head.getNext();
        this.head.setNext(first.getNext());
        // 判断一下 如果此时head为空
        if(this.head.getNext() == null) {
            // 如果链表的数据为空 那么需要将对应的尾指针修改为null 防止发生内存泄漏
            // 如果不移除会发生内存泄漏的问题
            // 实验验证一下
            // 验证发现 如果忘记清理双端链表最后的尾巴节点 会造成内存泄漏
            this.tail.setNext(null);
        }
        return first.getData();
    }

    /**
     * 展示双端链表
     */
    public void display() {
        System.out.print("[");
        LinkNode<T> node = this.head.getNext();
        while(node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println("]");
    }

}
