package com.zhangcy.data.structure;

/**
 * 使用链表实现
 * @author zhangcy
 */
public class LinkList<T> {

    /**
     * 链表的长度
     */
    private int size;

    /**
     * 提供方法返回链表的长度
     */
    public int size() {
        return size;
    }

    /**
     * 链表的头结点
     */
    private Node<T> first;

    /**
     * 获取指定位置的元素
     */
    public T get(int index) {
        checkRange(index);
        return getPositionNode(index).data;
    }

    /**
     * 在指定的位置插入数据
     */
    public void insert(int index, T data) {
        checkRange(index);
        if(first == null) {
            first = new Node<>(data, null);
        } else {
            Node<T> preNode = getPositionNode(index - 1);
            if(null == preNode) {
                preNode = new Node<>(data, first);
                first = preNode;
            } else {
                preNode.next = new Node<>(data, preNode.next);
            }
        }
        size++;
    }

    /**
     * 获取指定位置的Node节点对象
     */
    private Node<T> getPositionNode(int index) {
        Node<T> curr = first;
        for(int i = 1; i < index; i++) {
            curr = curr.next;
        }
        return curr;
}

    /**
     * 检查索引是否在正常的索引范围之内
     */
    private void checkRange(int index) {
        if(index < 0 || index - 1 > size) {
            throw new ArrayIndexOutOfBoundsException("index " + index + " is not correct");
        }
    }

    /**
     * 内部类Node节点
     */
    private static class Node<T> {

        /**
         * 数据
         */
        private T data;

        /**
         * 当前节点的下一个节点
         */
        private Node<T> next;

        Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }
}
