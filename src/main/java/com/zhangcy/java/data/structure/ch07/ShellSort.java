package com.zhangcy.java.data.structure.ch07;

import lombok.extern.slf4j.Slf4j;

/**
 * 希尔排序
 * 基于插入排序的 优化版的排序方式
 * 插入的排序方式时间复杂度(N*(logN)^2)
 *
 * 希尔排序也叫N-增量排序
 * 为啥希尔排序比正常的插入排序快呢？
 * 因为在极限的情况下，逆序的源数据排序会导致N*(N/2)的时间复杂度
 * 希尔排序一开始的间隔比较大，此时排序的跳转的间隔比较大，元素比较少
 * 当缩短了排序的间隔之后，元素已经基本有序了，此时插入排序的时间复杂度(N)
 * 怎么确定希尔排序的增量呢？
 * 优选的增量h=3*h+1
 * 第二选择的增量h=2*h
 * @author zhangcy
 */
@Slf4j
public class ShellSort<T extends Comparable<T>> {

    /**
     * 可以参与比较的数组
     */
    private Comparable[] arr;

    /**
     * 数组的最大长度
     */
    private int maxSize;

    /**
     * 数组的有效荷载长度
     */
    private int size;

    /**
     * 构造器
     */
    public ShellSort(int maxSize) {
        this.arr = new Comparable[maxSize];
        this.maxSize = maxSize;
        this.size = 0;
    }

    /**
     * 插入元素
     */
    public void insert(T data) {
        if(this.size > this.maxSize) {
            throw new IllegalArgumentException("arr is full");
        }
        arr[size++] = data;
    }

    /**
     * 展示数组中的元素
     */
    public void display() {
        System.out.print("[");
        for(int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }

    /**
     * 希尔排序
     */
    public void shellSort() {
        // 1 首先先选出第一个分组的间隔大小
        int h = 1;
        while(h < size) {
            h = 3 * h + 1;
        }
        // 2 按照指定的分组的大小的数据进行插入排序
        // 只要h的数据还大于等于1 那么就需要进行插入排序
        while(h >= 1) {
            // 3 从分组的中间开始 到末尾结束
            for(int outer = h; outer < size; outer++) {
                // 取出当前元素的位置
                Comparable temp = arr[outer];
                int inner = outer;
                // 进行数据的插入排序
                while(inner >= h && temp.compareTo(arr[inner - h]) < 0) {
                    arr[inner] = arr[inner - h];
                    inner = inner - h;
                }
                // 在inner的位置插入元素
                arr[inner] = temp;
            }
            // 缩小h的范围
            h = (h - 1) / 3;
        }
    }
}
