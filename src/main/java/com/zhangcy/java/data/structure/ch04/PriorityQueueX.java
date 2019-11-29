package com.zhangcy.java.data.structure.ch04;

/**
 * 优先级队列 高优先级队列 即元素值比较大的在前
 * 使用数组的方式显示优先级队列
 * @author zhangcy
 */
public class PriorityQueueX {

    /**
     * 存数据的数组
     */
    private int[] arr;

    /**
     * 队列的最大长度
     */
    private int maxSize;

    /**
     * 队列的尾部
     */
    private int rare;

    /**
     * 队列的元素的长度
     */
    private int currentIndex;

    /**
     * 初始化优先级队列
     */
    public PriorityQueueX(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.rare = 0;
        this.currentIndex = 0;
    }

    /**
     * 入队
     */
    public void inert(int data) {
        // 判断队列的长度
        if(this.rare == this.maxSize - 1) {
            throw new IllegalArgumentException("queue is full");
        }
        // 决定插入的位置
        // 默认低位在前
        // 类似于插入排序
        int i = rare;
        for(; i > 0 && arr[i - 1] > data; i--) {
            arr[i] = arr[i - 1];
        }
        arr[i] = data;
        this.rare++;
        this.currentIndex++;
    }

    /**
     * 出队 直接队首元素就是正确的
     */
    public int remove() {
        this.currentIndex--;
        return arr[--this.rare];
    }

    /**
     * 展示队列中的元素
     */
    public void display() {
        for(int i = 0; i < currentIndex; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
