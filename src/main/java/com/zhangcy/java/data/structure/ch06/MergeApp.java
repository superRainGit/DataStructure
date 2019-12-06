package com.zhangcy.java.data.structure.ch06;

/**
 * 归并排序
 * @author zhangcy
 */
public class MergeApp<T extends Comparable<T>> {

    /**
     * 数据存储位置
     */
    private Object[] arr;

    /**
     * 目前元素的长度
     */
    private int size;

    /**
     * 数组的最大长度
     */
    private int maxSize;

    /**
     * 数组
     */
    private Object[] temp;

    /**
     * 构造器
     */
    public MergeApp(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.arr = new Object[maxSize];
        this.temp = new Object[maxSize];
    }

    /**
     * 归并排序
     */
    public void mergeSort() {

    }

    /**
     * 递归的进行排序
     * @param head 从哪个位置开始
     * @param tail 到哪个位置结束
     */
    public void recurMergeSort(int head, int tail) {
        if(head == tail) {
            return;
        }
        // 找到中间的元素的位置
        int mid = (head + tail) / 2;
        // 先合并左边的元素
        recurMergeSort(head, mid);
        // 再合并右边的元素
        recurMergeSort(mid + 1, tail);
        // 最后需要合并两边的元素
    }

    /**
     * 合并当前数组指定范围内的有序数组
     * @param head 左边数组的位置
     * @param mid 中间数据的位置
     * @param tail 右边数组的位置
     */
    private void merge(int head, int mid, int tail) {
        // 需要生成新的数组
    }

    /**
     * 将两个有序的数组进行合并
     */
    public Object[] merge(T[] arrayA, T[] arrayB) {
        // 记录数组A的长度
        int aLen = 0;
        if(arrayA != null) {
            aLen = arrayA.length;
        }
        // 记录数组B的长度
        int bLen = 0;
        if(arrayB != null) {
            bLen = arrayB.length;
        }
        Object[] arrayC = new Object[aLen + bLen];
        int aCurr = 0;
        int bCurr = 0;
        int cCurr = 0;
        while(aCurr < aLen && bCurr < bLen) {
            T canInsert;
            if(arrayA[aCurr].compareTo(arrayB[bCurr]) > 0) {
                // 如果A数组取出的元素比B数组取出的元素大 那么需要放入的是B的元素
                canInsert = arrayB[bCurr++];
            } else {
                // 如果B数组取出的元素比A数组取出的元素大 那么需要放入的是A的元素
                canInsert = arrayA[aCurr++];
            }
            arrayC[cCurr++] = canInsert;
        }
        while (aCurr < aLen) {
            arrayC[cCurr++] = arrayA[aCurr++];
        }
        while (bCurr < bLen) {
            arrayC[cCurr++] = arrayB[bCurr++];
        }
        return arrayC;
    }
}
