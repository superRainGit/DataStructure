package com.zhangcy.java.data.structure.ch03;

/**
 * @author zhangcy
 * 冒泡排序
 */
public class ArrayBub {

    private long[] arr;

    private int currIndex;

    public ArrayBub(int maxSize) {
        arr = new long[maxSize];
        currIndex = 0;
    }

    public void insert(long insertKey) {
        arr[currIndex++] = insertKey;
    }

    public void display() {
        for (int i = 0; i < currIndex; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 冒泡排序
     * [3, 4, 1, 5, 7, 8, 11, 9]
     * 从开始排序开始 总计需要排序N次
     * 每次比较次数为N-1次
     * 总计时间复杂度 (N-1) + (N-2) + ... + 1
     * 交换的次数为 N^2
     * O(N^2)
     */
    public void bubbleSort() {
        int needSort = 0;
        for(int outer = currIndex - 1; outer > 1; outer--) {
            for(int current = 0; current < outer; current++) {
                if(arr[current] > arr[current + 1]) {
                    long temp = arr[current];
                    arr[current] = arr[current + 1];
                    arr[current + 1] = temp;
                    needSort = 1;
                }
            }
            if(needSort == 0) {
                break;
            }
        }
    }

    /**
     * 左右一起冒泡
     *
     */
    public void bubbleSort2() {
//        // 当前
//        int current = 0;
//        // 最左未排序的
//        int left = 0;
//        // 最右未排序的
//        int right = currIndex - 1;
//        while(left <= right) {
//            // 表明现在在排序的最左边
//            if(current == left) {
//                for(; current < right; current++) {
//                    if(arr[current] > arr[current + 1]) {
//                        long temp = arr[current];
//                        arr[current] = arr[current + 1];
//                        arr[current + 1] = temp;
//                    }
//                }
//                // 移动完成之后 需要将最右的排序的位置左移
//                right--;
//                current--;
//            } else {
//                for(; current > left; current--) {
//                    if(arr[current] < arr[current - 1]) {
//                        long temp = arr[current];
//                        arr[current] = arr[current - 1];
//                        arr[current - 1] = temp;
//                    }
//                }
//                left++;
//                current++;
//            }
//        }
        // 大牛写法
        // 在一个for循环里做了两个事情
        int leftOut = 0, rightOut = currIndex - 1, in;
        for(; rightOut > leftOut; rightOut--, leftOut++) {
            for(in = leftOut + 1; in < rightOut; in++) {
                if(arr[in] > arr[in + 1]) {
                    long temp = arr[in];
                    arr[in] = arr[in + 1];
                    arr[in + 1] = temp;
                }
            }
            for(in = rightOut - 1; in > leftOut; in--) {
                if(arr[in] < arr[in - 1]) {
                    long temp = arr[in];
                    arr[in] = arr[in - 1];
                    arr[in - 1] = temp;
                }
            }
        }
    }

    /**
     * 奇偶排序算法
     * 重复两趟扫描 第一趟扫描选择所有的数据项对 a[j] 和 a[j+1] j ∈ (1, 3 ,5...)
     * 第二趟扫描选择所有偶数数据项进行同样的操作 a[j] 和 a[j+1] j ∈ (2, 4, 6...)
     * 什么时候停止呢？ 当两趟排序的算法都不进行交换时数据即为有序数据
     *
     * [2, 5, 7, 3, 1, 8, 4]
     */
    public void oddEvenSort() {
        // 定义两个字段表明是否有数据替换发生
        boolean oddSwap = true;
        boolean evenSwap = true;
        while(oddSwap || evenSwap) {
            // 如果奇数比较数据项需要排序
            if(oddSwap) {
                boolean swap = false;
                for (int i = 1; i < currIndex - 1; i += 2) {
                    if(arr[i] > arr[i + 1]) {
                        long temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swap = true;
                    }
                }
                if(!swap) {
                    oddSwap = false;
                }
            }
            // 如果奇数比较数据项需要排序
            if(evenSwap) {
                boolean swap = false;
                for (int i = 0; i < currIndex - 1; i += 2) {
                    if(arr[i] > arr[i + 1]) {
                        long temp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = temp;
                        swap = true;
                    }
                }
                if(!swap) {
                    evenSwap = false;
                }
            }
        }
    }
}
