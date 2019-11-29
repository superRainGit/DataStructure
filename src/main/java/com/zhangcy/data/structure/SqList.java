package com.zhangcy.data.structure;

/**
 * 默认的使用数组实现链式存储数据结构
 * @author zhangcy
 */
public class SqList<T> {

    /**
     * 内置数组
     */
    private Object[] arr;

    /**
     * real data length
     */
    private int length;

    /**
     * 获取数组中数据的长度
     */
    public int size() {
        return length;
    }

    /**
     * 支持长度的构造器
     */
    public SqList(int size) {
        arr = new Object[size];
        length = 0;
    }

    /**
     * 获取指定位置的元素
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkRange(index);
        return (T) arr[index - 1];
    }

    /**
     * 在指定位置插入一个元素
     */
    public void insert(int index, T t) {
        checkSize();
        checkRange(index - 1);
        // 如果在最尾端插入数据
        if(index < size()) {
            // 需要在指定位置插入数据 但是要进行后面位置的数据的移动
            System.arraycopy(arr, index - 1, arr, index, size() - index + 1);
        }
        arr[index - 1] = t;
        length++;
    }

    /**
     * 删除指定位置的元素
     */
    @SuppressWarnings("unchecked")
    public T delete(int index) {
        T t = null;
        checkRange(index - 1);
        t = (T) arr[index - 1];
        if(index < size()) {
            System.arraycopy(arr, index, arr, index - 1,size() - index);
            arr[size() - 1] = null;
        } else {
            arr[index - 1] = null;
        }
        length--;
        return t;
    }

    /**
     * 判断取值的长度是否超过了数组的长度
     */
    private void checkRange(int index) {
        if(index < 0 || index >= arr.length) {
            throw new ArrayIndexOutOfBoundsException("index " + index + " is not correct");
        }
    }

    private void checkSize() {
        if(size() >= arr.length) {
            throw new IllegalStateException("element is full");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Object a : arr) {
            sb.append(a).append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
