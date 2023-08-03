package com.zhangcy.java.data.structure.ch03;

/**
 * 选择排序
 * @author zhangcy
 */
public class ArraySel {

    private long[] arr;

    private int currIndex;

    public ArraySel(int maxSize) {
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
     * 选择排序
     * [1, 4, 3, 9, 11, 18, 15, 14]
     * 每次从数据的右边筛选出最小的元素 和数组的最左边做交换
     * 比较次数为 (N-1) + (N-2) + ... + 1
     * 交换次数为 N
     * 比冒泡的交换次数N^2要好
     */
    public void selectSort() {
        for (int j = 0; j < currIndex; j++) {
            int minPos = j;
            for(int i = j + 1; i < currIndex; i++) {
                if(arr[i] < arr[minPos]) {
                    minPos = i;
                }
            }
            long temp = arr[j];
            arr[j] = arr[minPos];
            arr[minPos] = temp;
        }
    }

}
