package com.zhangcy.java.data.structure.ch06;

/**
 * 递归实现二分查找
 * @author zhangcy
 */
public class BinarySearchApp<T extends Comparable<T>> {

    /**
     * 内容数组
     */
    private Object[] arr;

    /**
     * 数组长度
     */
    private int size;

    /**
     * 构造器
     */
    public BinarySearchApp(int maxSize) {
        this.arr = new Object[maxSize];
        this.size = 0;
    }

    /**
     * 插入数据 使用插入排序进行插入
     */
    public void insert(T data) {
        if(this.arr.length == size) {
            throw new IllegalArgumentException("arr is empty");
        }
        int curr = 0;
        for(; curr < size; curr++) {
            T currentEle = (T) arr[curr];
            // 如果要插入的元素比当前遍历到的元素小 那么需要移动元素
            if(data.compareTo(currentEle) < 0) {
                break;
            }
        }
        // 移动元素
        System.arraycopy(this.arr, curr, this.arr, curr + 1, size - curr);
        arr[curr] = data;
        size++;
    }

    /**
     * 查找数据
     * 递归的进行二分查找
     */
    public boolean find(T searchKey) {
        return recursionFind(searchKey, 0, size - 1);
    }

    /**
     * 递归的查找
     * 为啥不直接提供递归的方法呢
     * 因为对于上层的使用发来说 不会关注从元素的那个位置开始查找
     * @param head 从哪个位置开始
     * @param tail 到哪个位置结束
     */
    private boolean recursionFind(T search, int head, int tail) {
        // 当头部位置查找的地点大于尾部的位置时 直接返回
        if(head > tail) {
            return false;
        }
        int mid = (head + tail) / 2;
        // 取出中值元素
        T currentData = (T) arr[mid];
        if(currentData.compareTo(search) == 0) {
            return true;
        }
        if(currentData.compareTo(search) > 0) {
            // 如果当前元素比较大
            // 相当于要从数据的左半部分开始查找
            return recursionFind(search, head,  mid - 1);
        } else {
            // 如果当前元素比较小
            // 相当于要从数据的右半部分开始查找
            return recursionFind(search, mid + 1, tail);
        }
    }

    /**
     * 展示元素
     */
    public void display() {
        System.out.print("has elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
