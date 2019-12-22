package com.zhangcy.java.data.structure.ch07;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 快速排序算法实现
 * @author zhangcy
 */
@Slf4j
public class QuickSortApp<T extends Comparable<T>> {

    /**
     * 数据仓库
     */
    private Comparable[] arr;

    /**
     * 数据仓库的最大长度
     */
    private int maxSize;

    /**
     * 有效荷载数据的长度
     */
    private int size;

    /**
     * 构造器
     */
    public QuickSortApp(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new Comparable[maxSize];
        this.size = 0;
    }

    /**
     * 插入数据
     */
    public void insert(T data) {
        if(size > maxSize) {
            throw new IllegalArgumentException("arr is full");
        }
        arr[size++] = data;
    }

    /**
     * 快速排序
     */
    public void quickSort() {
        recursiveQuickSort(0, this.size - 1);
    }

    /**
     * 内部递归版的快速排序
     * @param left 需要排序的子数组的左索引
     * @param right 需要排序的子数组的右索引
     */
    private void recursiveQuickSort(int left, int right) {
        // 什么时候结束呢? 当左边元素的值小于等于右面的索引
        if(left >= right) {
            return;
        }
        // 1 先分组  选定哪个作为分组的排序项
        int partIndex = partitionIt(left, right);
        // 2 递归的进行左边子数组的快排
        recursiveQuickSort(left, partIndex - 1);
        // 3 递归的进行右边子数组的快排
        recursiveQuickSort(partIndex + 1, right);
    }

    /**
     * 分组在left-right子数组内的元素
     * @param left 需要分组的子数组的左索引
     * @param right 需要分组的子数组的右索引
     * @return 返回哪个标榜元素的索引的位置
     */
    private int partitionIt(int left, int right) {
        // 1 可以直接以子数组最左边的元素作为起始
        Comparable partLine = arr[left];
        // 2 根据当前元素进行分组
        int leftScan = left + 1;
        int rightScan = right;
        while(true) {
            // 因为要保证参考数据插入的位置是正确的 从左向右的到最右边需要截止 因为是从左边的位置进行的取值
            while(leftScan <= right && arr[leftScan].compareTo(partLine) < 0) {
                if(leftScan == right) {
                    break;
                }
                leftScan++;
            }
            // 但是当从右向左的时候 因为左边已经有一个占位了 所以要多算一个
            while(rightScan >= left + 1 && arr[rightScan].compareTo(partLine) > 0) {
                rightScan--;
            }
            if(leftScan >= rightScan) {
                break;
            }
            // 数组进行交换
            Comparable temp = arr[leftScan];
            arr[leftScan] = arr[rightScan];
            arr[rightScan] = temp;
            leftScan++;
            rightScan--;
        }
        // 找到了最终落脚的点
        // 需要进行数组的移动
        // 数组的拷贝太垃圾 效率比较低 因为不需要保证分出来的数据是有序的 那么就直接可以交换 这样多省事
//        System.arraycopy(arr, left + 1, arr, left, leftScan - 1 - left);
        // 将基准线元素插入到数组中
        Comparable temp = arr[rightScan];
        arr[rightScan] = partLine;
        arr[left] = temp;
        return rightScan;
    }

    /**
     * 数据的展示
     */
    public void display() {
        log.info("display = {}", Arrays.asList(arr).subList(0, size));
    }
}
