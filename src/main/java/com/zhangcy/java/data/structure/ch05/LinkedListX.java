package com.zhangcy.java.data.structure.ch05;

import lombok.Getter;

/**
 * 单链表 单向链表 适合头插法
 * 可以使用尾插插入数据
 * @author zhangcy
 */
@Getter
public class LinkedListX<T> {

    /**
     * 指向链结点头结点的指针
     */
    private LinkNode<T> first;

    /**
     * 初始化头结点
     */
    public LinkedListX() {
        // 这个元素的值取决怎么理解头结点的含义
        // 如果头结点只有位置含义 那么数据域没有意义
        // 如果头结点表示第一个元素 那么需要更改
        this.first = new LinkNode<>(null, null);
    }

    /**
     * 在链表中查找是否存在指定的元素
     */
    public boolean searchData(T data) {
        // 第一个元素
        LinkNode<T> node = this.first.getNext();
        while(node != null) {
            // 判断当前节点的数据域和指定元素的数据域是否相同
            if(node.getData().equals(data)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * 删除指定的数据域
     * 返回结果表明是否存在该元素且删除成功
     */
    public boolean deleteData(T data) {
        // 第一个元素
        LinkNode<T> node = this.first.getNext();
        // 头结点 其实指向的是当前遍历的结点的前结点
        LinkNode<T> previous = this.first;
        while(node != null) {
            // 判断当前节点的数据域和指定元素的数据域是否相同
            if(node.getData().equals(data)) {
                // 删除当前元素 且让其他元素连接起来形成链表
                // 怎么找到当前元素前面的元素?
                previous.setNext(node.getNext());
                return true;
            }
            previous = node;
            node = node.getNext();
        }
        return false;
    }

    /**
     * 遍历显示链表的内容
     */
    public void display() {
        System.out.print("[");
        LinkNode node = this.first.getNext();
        while(node != null) {
            System.out.print(node.getData() + ", ");
            // 向后移动一个节点
            node = node.getNext();
        }
        System.out.print("]");
        System.out.println();
    }

    /**
     * 从链表的头部移除一个元素
     */
    public T removeHead() {
        // 先获取头结点的下一个元素
        LinkNode<T> next = this.first.getNext();
        if(next == null) {
            throw new IllegalArgumentException("link is empty");
        }
        this.first.setNext(next.getNext());
        return next.getData();
    }

    /**
     * 在头结点位置插入一个元素
     */
    public void insertHead(T data) {
        LinkNode<T> newNode;
        newNode = new LinkNode<>(data, this.first.getNext());
        this.first.setNext(newNode);
    }

    /**
     * 由链表内部直接返回迭代器是非常合理的
     * 迭代器在实际使用时一般直接作为集合的内部类去实现
     * 现在直接先作为外部类去做
     */
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }
}
