package com.zhangcy.java.data.structure.ch05;

/**
 * 有序单链表
 * @author zhangcy
 */
public class SortedListX<T extends Comparable<T>> {

    /**
     * 头节点
     * 没有数据含义的头结点
     */
    private LinkNode<T> head;

    /**
     * 无参数构造器
     */
    public SortedListX() {
        this.head = new LinkNode<>(null, null);
    }

    /**
     * 有序链表的插入
     * 首先需要找到需要插入的位置
     * 将对应的节点插入
     */
    public void insert(T data) {
        // 1 先生成当前要插入的元素的链结点 数据域为数据 next直接指为空
        LinkNode<T> linkNode = new LinkNode<>(data, null);
        // 2 从原来的链表中找到要插入的节点的位置
        LinkNode<T> current = this.head.getNext();
        LinkNode<T> previous = this.head;
        while(current != null) {
            // 比较链表数数据域的大小 如果当前的元素比要插入的元素大 那么已经找到了插入的位置
            if(current.getData().compareTo(data) > 0) {
                // 在current之前插入
                break;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        // 3 判断previous 如果这个为头结点 那么表示第一个元素就比要插入的元素大
        if(previous == this.head) {
            // 直接采用头插法将链结点进行插入
            linkNode.setNext(current);
            this.head.setNext(linkNode);
        } else {
            // 如果previous不为空 那么至少移动过一部分的元素 此时需要再current之前【或者是previous之后】
            // 进行数据的插入
            previous.setNext(linkNode);
            linkNode.setNext(current);
        }
    }

    /**
     * 在头结点的位置移除元素
     */
    public T removeHead() {
        LinkNode<T> current = this.head.getNext();
        if(current != null) {
            this.head.setNext(current.getNext());
            return current.getData();
        }
        return null;
    }

    /**
     * 移除指定元素
     * @return 返回元素是否存在且移除成功
     */
    public boolean remove(T data) {
        LinkNode<T> current = this.head.getNext();
        LinkNode<T> previous = this.head;
        // 从头结点开始进行数据的遍历
        while(current != null) {
            if(current.getData().equals(data)) {
                // 找到了对应的元素
                previous.setNext(current.getNext());
                return true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        return false;
    }

    /**
     * 有序数组的展示
     */
    public void display() {
        System.out.print("[");
        LinkNode<T> current = this.head.getNext();
        while(current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println("]");
    }
}
