package com.zhangcy.java.data.structure.ch05;

/**
 * 循环链表
 * @author zhangcy
 */
public class LoopLinkedList<T> {

    /**
     * 循环链表的当前节点
     */
    private LinkNode<T> current;

    /**
     * 初始化链表
     */
    public LoopLinkedList() {
        this.current = null;
    }

    /**
     * 在循环链表中插入
     */
    public void insert(T data) {
        // 新插入的节点 其实就相当于在当前节点的后面进行插入 同时更新当前节点为新插入的节点
        LinkNode<T> node = new LinkNode<>(data, null);
        // 空的链表
        if(isEmpty()) {
            this.current = node;
            this.current.setNext(node);
        } else {
            // 先让创建的新节点指向后面的节点
            node.setNext(this.current.getNext());
            // 如果不是空的链表
            this.current.setNext(node);
            this.current = this.current.getNext();
        }
    }

    /**
     * 从当前指针指向的元素中查找指定的元素
     */
    public boolean search(T data) {
        // 如果不是空的循环链表
        if(!isEmpty()) {
            // 先获取当前指向的链结点
            LinkNode<T> currentNode = this.current;
            while(currentNode.getNext() != this.current) {
                if(currentNode.getData().equals(data)) {
                    return true;
                }
                // 依次步进
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }

    /**
     * 将当前元素向后移动一个位置
     */
    public void step() {
        this.current = this.current.getNext();
    }

    /**
     * 删除当前节点后面的元素
     */
    public T delete() {
        if(isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        // 先获取当前节点的下一个节点
        LinkNode<T> currentNextNode = this.current.getNext();
        // 如果取到的下一个元素和当前元素一致 那么表明内部就一个元素了 此时可以将current置为null
        if(currentNextNode == this.current) {
            this.current = null;
        } else {
            this.current.setNext(currentNextNode.getNext());
        }
        return currentNextNode.getData();
    }

    /**
     * 判断循环链表的为空
     */
    public boolean isEmpty() {
        return this.current == null;
    }

    /**
     * 从当前元素展示循环队列
     */
    public void display() {
        System.out.print("queue has elements:");
        if(!isEmpty()) {
            LinkNode<T> currentNode = this.current;
            do {
                System.out.print(currentNode.getData() + " ");
                // 依次步进
                currentNode = currentNode.getNext();
            } while(currentNode != this.current);
        }
        System.out.println();
    }
}
