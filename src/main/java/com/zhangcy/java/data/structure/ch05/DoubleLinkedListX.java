package com.zhangcy.java.data.structure.ch05;

/**
 * 双向链表
 * 双向链表需要注意当修改的是头结点和尾结点时
 * 除了修改头尾节点的next或者previous
 * 还需要注意修改下一个节点的前驱后继指针
 * @author zhangcy
 */
public class DoubleLinkedListX<T> {

    /**
     * 头节点
     */
    private DoubleLinkNode<T> head;

    /**
     * 尾结点
     */
    private DoubleLinkNode<T> tail;

    /**
     * 构造器
     */
    public DoubleLinkedListX() {
        this.head = new DoubleLinkNode<>(null ,null, null);
        this.tail = new DoubleLinkNode<>(null ,null, null);
    }

    /**
     * 删除和指定元素相同的链结点
     */
    public boolean deleteKey(T key) {
        if(isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        // 从头节点位置开始查找
        DoubleLinkNode<T> current = this.head.getNext();
        while(current != null) {
            if(current.getData().equals(key)) {
                break;
            }
            current = current.getNext();
        }
        // 表示找到了元素
        if(current != null) {
            // 如果是最后一个元素
            if(current.getNext() == null) {
                // 表示找到了最后一个节点
                // 需要改变尾结点指向
                DoubleLinkNode<T> currPrevious = current.getPrevious();
                currPrevious.setNext(null);
                this.tail.setNext(currPrevious);
            } else if(this.head == current.getPrevious()) {
                // 如果不是最后一个元素 是第一个元素
                // 需要改变头结点的指向
                DoubleLinkNode<T> nextNode = current.getNext();
                nextNode.setPrevious(this.head);
                this.head.setNext(nextNode);
            } else {
                // 表示在中间节点找到了指定的元素
                DoubleLinkNode<T> currPrevious = current.getPrevious();
                currPrevious.setNext(current.getNext());
                current.getNext().setPrevious(currPrevious);
            }
            // 需要统一处理当数据为最后一个元素时 手动的修改head和tail的指向
            if(tail.getNext() == current && this.head.getNext() == current) {
                this.tail.setNext(null);
                this.head.setNext(null);
            }
            return true;
        }
        return false;
    }

    /**
     * 从尾结点开始删除元素
     */
    public T deleteLast() {
        if(isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        DoubleLinkNode<T> tail = this.tail.getNext();
        // 如果尾结点指向的上一个节点元素为头结点
        // 保证数据一致性 防止出现内存泄漏
        if(this.head == tail.getPrevious()) {
            this.head.setNext(null);
            this.tail.setNext(null);
        } else {
            DoubleLinkNode<T> tailPrevious = tail.getPrevious();
            tailPrevious.setNext(null);
            this.tail.setNext(tailPrevious);
        }
        return tail.getData();
    }

    /**
     * 删除头结点数据
     */
    public T deleteFirst() {
        if(isEmpty()) {
            throw new IllegalArgumentException("list is empty");
        }
        DoubleLinkNode<T> head = this.head.getNext();
        // 如果下一个节点为空
        if(null == head.getNext()) {
            // 保证数据一致性 防止出现内存泄漏
            this.tail.setNext(null);
        }
        this.head.setNext(head.getNext());
        return head.getData();
    }

    /**
     * 在指定元素位置的后面插入元素
     * 如果找不到元素 那么就插在尾结点上
     * @param key 指定的元素
     * @param data 需要插入的元素
     */
    public void insertAfter(T key, T data) {
        // 先生成链结点
        DoubleLinkNode<T> newNode = new DoubleLinkNode<>(data, null, null);
        // 从头至尾遍历链表
        if(isEmpty()) {
            // 如果双向链表的长度为空  那么可以直接把元素要插入的位置进行补入
            newNode.setPrevious(this.head);
            this.head.setNext(newNode);
            this.tail.setNext(newNode);
        } else {
            DoubleLinkNode<T> current = this.head.getNext();
            // 遍历元素
            while(current != null) {
                if(current.getData().equals(key)) {
                    break;
                }
                current = current.getNext();
            }
            // 在指定元素的后面插入元素
            // 如果遍历到最后找不到指定的元素
            // 直接在表尾插入
            if(current == null || this.tail.getNext() == current) {
                // 取出最后元素
                DoubleLinkNode<T> last = this.tail.getNext();
                last.setNext(newNode);
                newNode.setPrevious(last);
                this.tail.setNext(newNode);
            } else {
                // 先管理新增元素的起始 用于保存链表的链接
                newNode.setNext(current.getNext());
                newNode.setPrevious(current);
                current.getNext().setPrevious(newNode);
                current.setNext(newNode);
            }
        }
    }

    /**
     * 在表尾插入数据
     */
    public void insertLast(T data) {
        // 先生成链结点
        DoubleLinkNode<T> newNode = new DoubleLinkNode<>(data, null, null);
        // 尾结点没有数据
        if(isEmpty()) {
            // 修正this.head的指针的位置
            this.head.setNext(newNode);
            newNode.setPrevious(this.head);
        } else {
            // 找到尾结点位置的数据
            DoubleLinkNode<T> node = this.tail.getNext();
            node.setNext(newNode);
            newNode.setPrevious(node);
        }
        this.tail.setNext(newNode);
    }

    /**
     * 在表头插入数据
     */
    public void insertFirst(T data) {
        // 先生成链结点
        DoubleLinkNode<T> newNode = new DoubleLinkNode<>(data, null, null);
        // 如果头节点为空
        if(isEmpty()) {
            // 修改新生成的节点的前后地址
            // 需要将尾结点也加上
            this.tail.setNext(newNode);
        } else {
            // 如果头节点不为空
            // 判断要插入的位置
            // 尾结点不需要动
            DoubleLinkNode<T> node = this.head.getNext();
            newNode.setNext(node);
            node.setPrevious(newNode);
        }
        // 修改新链结点的头尾指针
        this.head.setNext(newNode);
        newNode.setPrevious(this.head);
    }

    /**
     * 数据展示
     */
    public void display() {
        DoubleLinkNode<T> node = this.head.getNext();
        System.out.print("[");
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.getNext();
        }
        System.out.println("]");
    }

    /**
     * 判断链表是否是个空链表
     */
    public boolean isEmpty() {
        return this.head.getNext() == null && this.tail.getNext() == null;
    }
}
