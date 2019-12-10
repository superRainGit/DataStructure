package com.zhangcy.java.data.structure.ch06;

/**
 * 栈
 * @author zhangcy
 */
public class StackApp<T> {

    /**
     * 存放的数据
     */
    private T[] arr;

    /**
     * 栈的最大长度
     */
    private int maxSize;

    /**
     * 栈顶的指针
     */
    private int top;

    /**
     * 构造器 初始化栈顶元素在数组的最末尾
     * @param maxSize 最大的栈的长度
     */
    public StackApp(int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
    }

    /**
     * 将元素推入栈中
     * @param data 数据元素
     */
    public void push(T data) {
        if(++this.top >= maxSize) {
            throw new IllegalArgumentException("stack is full");
        }
        arr[this.top] = data;
    }

    /**
     * 将栈顶元素出栈
     * @return 栈顶元素的值
     */
    public T pop() {
        if(this.top == -1) {
            throw new IllegalArgumentException("stack is empty");
        }
        return arr[this.top--];
    }

    /**
     * 观看一下栈顶的元素
     * @return 栈顶元素的值
     */
    public T peek() {
        if(this.top == -1) {
            throw new IllegalArgumentException("stack is empty");
        }
        return arr[this.top];
    }
}
