package com.zhangcy.java.data.structure.ch04;

/**
 * 实现双端队列
 * @author zhangcy
 */
public class DequeX<T> {

    /**
     * 存放数据的元素
     */
    private Object[] arr;

    /**
     * 目前数组中有效的数据长度
     */
    private int size;

    /**
     * 队列的最大长度
     */
    private int maxSize;

    /**
     * 队头元素指针
     */
    private int font;

    /**
     * 队尾元素指针
     */
    private int rare;

    /**
     * 初始化最大长度的队列
     */
    public DequeX(int maxSize) {
        arr = new Object[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
        this.font = 0;
        this.rare = 0;
    }

    /**
     * 在队头插入元素
     */
    public void insertLeft(T data) {
        // 如果队列内元素的大小大于队列的长度-1
        if(this.font == (this.rare + 1) % this.maxSize) {
            throw new IllegalArgumentException("queue is full");
        }
        // 如果是在队头插入元素
        this.font = (this.font - 1 + this.maxSize) % this.maxSize;
        this.arr[this.font] = data;
        this.size++;
    }

    /**
     * 在队列的尾部插入元素
     */
    public void insertRight(T data) {
        // 如果队列内元素的大小大于队列的长度-1
        if(this.font == (this.rare + 1) % this.maxSize) {
            throw new IllegalArgumentException("queue is full");
        }
        // 在队尾插入元素
        this.arr[this.rare] = data;
        this.rare = (this.rare + 1) % this.maxSize;
        this.size++;
    }

    /**
     * 从队头移除元素
     */
    @SuppressWarnings("unchecked")
    public T removeLeft() {
        // 队列中元素的为空
        if(this.font == this.rare) {
            throw new IllegalArgumentException("queue is empty");
        }
        T data = (T) this.arr[this.font];
        this.font = (this.font + 1) % this.maxSize;
        this.size--;
        return data;
    }

    /**
     * 从队尾移除元素
     */
    @SuppressWarnings("unchecked")
    public T removeRight() {
        // 队列中元素的为空
        if(this.font == this.rare) {
            throw new IllegalArgumentException("queue is empty");
        }
        this.rare = (this.rare - 1 + this.maxSize) % this.maxSize;
        this.size--;
        return (T) this.arr[this.rare];
    }

    /**
     * 展示队列中的所有的可用的元素
     */
    public void display() {
        for (int i = this.font; i != this.rare; i = (i + 1) % this.maxSize) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 队列中有效数组的元素个数
     */
    public int size() {
        return this.size;
    }
}
