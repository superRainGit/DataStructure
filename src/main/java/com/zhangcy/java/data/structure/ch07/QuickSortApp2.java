package com.zhangcy.java.data.structure.ch07;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 快速排序的第二版[尝试去掉在选择中枢时边界的判断]
 * @author zhangcy
 */
@Slf4j
public class QuickSortApp2<T extends Comparable<T>> {

    /**
     * 数据仓库
     */
    private Comparable[] arr;

    /**
     * 仓库的最大容量
     */
    private int maxSize;

    /**
     * 有效的荷载容量
     */
    private int size;

    /**
     * 构造器
     */
    public QuickSortApp2(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new Comparable[maxSize];
        // 虽然没有必要 但是给一个默认值
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
     * 内部递归的快速排序
     * @param left 递归排序的子数组的左边的边界
     * @param right 递归排序的子数组的右边的边界
     */
    private void recursiveQuickSort(int left, int right) {
        if(left >= right) {
            return;
        }
        // 先找到划分算法的值所在的位置
        int partIndex = partitionIt(left, right);
        // 递归的划分左侧的子数组
        recursiveQuickSort(left, partIndex - 1);
        // 递归的划分右侧的子数组
        recursiveQuickSort(partIndex + 1, right);
    }

    /**
     * 找到划分算法的值所在的位置
     * @param left 子数组的左边的边界
     * @param right 子数组的右边的边界
     * @return 划分算法最终计算的位置
     */
    private int partitionIt(int left, int right) {
        // 假设仍以左边元素作为判断的基本数据
        // 让索引的范围从待索引的两个外端开始
        int leftScan = left;
        int rightScan = right + 1;
        while(true) {
            // 这次先从右开始找 因为除了判断数据的大小之外还判断了数字的边界 那么可以去掉么?
            // 从右边找是可以去掉的，因为基准数字在最左边 当右边向左到最左边的时候 已经因为判断大小的条件而不满足了
            while(arr[--rightScan].compareTo(arr[left]) > 0) {

            }
            // 但是从左向右时 需要判断一下索引的边界 因为此时没有有右端的数据做限制了
            // 后期可以考虑在数组的双端做限制 这样就很省心了
            while(leftScan < right && arr[++leftScan].compareTo(arr[left]) < 0) {

            }
            // 当两个扫描相遇的时候 就是找到了元素的位置的时候
            if(leftScan >= rightScan) {
                break;
            }
            // 进行数据的交换
            Comparable temp = arr[rightScan];
            arr[rightScan] = arr[leftScan];
            arr[leftScan] = temp;
            // 因为上面比较的时候已经有做过加减了
        }
        // 最后交换找到的位置和最左边的元素
        Comparable temp = arr[left];
        arr[left] = arr[rightScan];
        arr[rightScan] = temp;
        return rightScan;
    }

    /**
     * 有效荷载数据的展示
     */
    public void display() {
        log.info("display {}", Arrays.asList(arr).subList(0, size));
    }
}
