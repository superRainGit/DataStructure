package com.zhangcy.java.data.structure.ch07;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 快速排序算法实现三
 * 该算法是应对当数组出现倒序或者出现大量的N-1和1个元素的分组
 * 同时也能去掉在遍历的时候对数组下标的检测
 * 这个时候的取值就变为了在三个地方进行中枢的获取
 * @author zhangcy
 */
@Slf4j
public class QuickSortApp3<T extends Comparable<T>> {

    /**
     * 数据仓库
     */
    private Comparable[] arr;

    /**
     * 数据仓库的最大长度
     */
    private int maxSize;

    /**
     * 数据仓库的有效荷载长度
     */
    private int size;

    /**
     * 构造器
     */
    public QuickSortApp3(int maxSize) {
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
        recursiveQuickSort(0, size - 1);
    }

    /**
     * 递归的进行排序
     * @param left 子数组的左边的边界
     * @param right 子数组的右边的边界
     */
    private void recursiveQuickSort(int left, int right) {
        // 什么时候结束呢？
        // 当分组的元素的长度小于3的时候就可以手动排序并且结束了
        int subArrLen = right - left + 1;
        if(subArrLen <= 3) {
            manualSort(left, right, subArrLen);
            return;
        }
        int median = medianOf3(left, right);
        int partIndex = partitionIt(median, right);
        recursiveQuickSort(left, partIndex - 1);
        recursiveQuickSort(partIndex + 1, right);
    }

    /**
     * 手动的排序
     */
    private void manualSort(int left, int right, int len) {
        // 如果要排序的数组长度为1
        if(len == 1) {
            return;
        }
        // 如果长度为2
        if(len == 2) {
            if(arr[left].compareTo(arr[right]) > 0) {
                swap(left, right);
            }
            return;
        }
        // 如果长度为三
        if(len == 3) {
            // 对三个元素进行排序
            if(arr[left].compareTo(arr[right - 1]) > 0) {
                swap(left, right - 1);
            }
            if(arr[left].compareTo(arr[right - 1]) > 0) {
                swap(left, right - 1);
            }
            if(arr[right - 1].compareTo(arr[right]) > 0) {
                swap(right - 1, right);
            }
        }
    }

    /**
     * 在数组中查找适合的中枢的位置
     * @param left 子数组的左索引的位置
     * @param right 子数组的右索引的位置
     * @return 返回找到的适合的中枢的应在的位置
     */
    private int medianOf3(int left, int right) {
        // 在该算法中比较的是最左边的元素 最右边的元素 中间的元素
        // 找出这三个位置中满足条件的作为中枢 然后计算中枢应该在的位置
        int median = (left + right) / 2;
        // 对三个元素进行排序
        if(arr[left].compareTo(arr[median]) > 0) {
            swap(left, median);
        }
        if(arr[left].compareTo(arr[right]) > 0) {
            swap(left, right);
        }
        if(arr[median].compareTo(arr[right]) > 0) {
            swap(median, right);
        }
        // 已经找到了最适合的元素 那么可以直接把参考元素换到开始
        swap(left + 1, median);
        return left + 1;
    }

    /**
     * 进行划分算法
     */
    private int partitionIt(int left, int right) {
        int leftScan = left;
        int rightScan = right + 1;
        while(true) {
            // 从右边开始查找
            while(arr[--rightScan].compareTo(arr[left]) > 0) {

            }
            // 从左边查找
            while(arr[++leftScan].compareTo(arr[left]) < 0) {

            }
            if(leftScan >= rightScan) {
                break;
            }
            // 交换leftScan和rightScan
            swap(leftScan, rightScan);
        }
        // 最后交换两个地方的元素
        swap(left, rightScan);
        return rightScan;
    }

    /**
     * 交换数组的两个元素的位置
     */
    private void swap(int left, int right) {
        Comparable temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * 展示有效荷载的数据
     */
    public void display() {
        log.info("display {}", Arrays.asList(arr).subList(0, size));
    }
}
