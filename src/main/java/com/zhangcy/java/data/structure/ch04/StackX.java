package com.zhangcy.java.data.structure.ch04;

/**
 * 使用数组实现一个栈
 * @author zhangcy
 */
public class StackX<T> {

    /**
     * 数据的长度
     */
    private int currIndex;

    /**
     * 数组
     */
    private Object[] chars;

    /**
     * 指向栈顶的指针
     */
    private int top;

    public StackX(int maxSize) {
        chars = new Object[maxSize];
        currIndex = 0;
        // 默认指向栈底
        top = -1;
    }

    /**
     * 入栈
     */
    public void push(T data) {
        chars[++top] = data;
        currIndex++;
    }

    /**
     * 出栈
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        currIndex--;
        return (T) chars[top--];
    }

    /**
     * 看一下栈顶的元素
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if(top < 0) {
            throw new IllegalArgumentException();
        }
        return (T) chars[top];
    }

    /**
     * 展示
     */
    public void display() {
        for (int i = 0;i < currIndex; i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println();
    }

    /**
     * 判断是否是空的栈
     */
    public boolean isEmpty() {
        return currIndex <= 0;
    }

}
