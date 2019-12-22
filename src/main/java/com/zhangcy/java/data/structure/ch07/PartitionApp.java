package com.zhangcy.java.data.structure.ch07;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 快速排序之前的划分方法
 * @author zhangcy
 */
@Slf4j
public class PartitionApp<T extends Comparable<T>> {

    /**
     * 可以参与比较的数组
     */
    private Comparable<T>[] arr;

    /**
     * 数组的最大的长度
     */
    private int maxSize;

    /**
     * 数组的有效荷载长度
     */
    private int size;

    /**
     * 构造器
     */
    public PartitionApp(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new Comparable[maxSize];
        this.size = 0;
    }

    /**
     * 插入数据
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
        log.info("display: {}", Arrays.asList(arr).subList(0, size));
    }

    /**
     * 按照指定的大小数据的分组
     */
    public void partitionIt(T splitLine) {
        int leftScan = 0;
        int rightScan = this.size - 1;
        // 当左右扫描遇到的时候就是结束的时候
        while(true) {
            // 先从左找第一个大于splitLine的
            while(leftScan < size && arr[leftScan++].compareTo(splitLine) < 0) {
            }
            // 再从右找第一个小于splitLine的
            while(rightScan > -1 && arr[rightScan--].compareTo(splitLine) > 0) {
            }
            if(rightScan < leftScan) {
                break;
            }
            // 交换两个元素的位置
            Comparable temp = arr[leftScan - 1];
            arr[leftScan - 1] = arr[rightScan + 1];
            arr[rightScan + 1] = temp;
        }
    }
}
