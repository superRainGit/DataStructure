package com.zhangcy.java.data.structure.ch06;

/**
 * 归并排序
 * 归并排序的时间复杂度的分析
 * 归并排序的时间复杂度为[N*logN]
 * 如何计算出来的呢？
 * 考虑复制的次数：
 *  当元素有两个时 需要复制两次
 *  当元素有四个时 需要复制2 + 2 + 4 = 8 次
 *  当元素有八个时 需要复制2 + 2 + 4 + 2 + 2 + 4 + 8 = 24次
 *
 * 比较的次数
 *  比较的次数总是比复制的次数要少
 *  最好的情况是需要合并的数组的长度的一半
 *  最差的情况是比要合并的数组的长度减一
 * @author zhangcy
 */
public class MergeApp<T extends Comparable<T>> {

    /**
     * 数据存储位置
     */
    private Comparable[] arr;

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
    private Comparable[] temp;

    /**
     * 构造器
     */
    public MergeApp(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.arr = new Comparable[maxSize];
        this.temp = new Comparable[maxSize];
    }

    /**
     * 插入数据
     */
    public void insert(T data) {
        if(this.size > this.maxSize) {
            throw new IllegalArgumentException("array is full");
        }
        this.arr[size++] = data;
    }

    /**
     * 归并排序
     */
    public void mergeSort() {
        this.recurMergeSort(0, size - 1);
    }

    /**
     * 递归的进行排序
     * @param head 从哪个位置开始
     * @param tail 到哪个位置结束
     */
    private void recurMergeSort(int head, int tail) {
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
        merge(head, mid, tail);
    }

    /**
     * 合并当前数组指定范围内的有序数组
     * 要注意的是这个方法的参数是数组的下标
     * @param head 左边数组的位置
     * @param mid 中间数据的位置
     * @param tail 右边数组的位置
     */
    private void merge(int head, int mid, int tail) {
        // 进行两个有序数组的合并
        int currA = head;
        int currB = mid + 1;
        int currC = head;
        while(currA <= mid && currB <= tail) {
            if(this.arr[currA].compareTo(this.arr[currB]) > 0) {
                temp[currC++] = this.arr[currB++];
            } else {
                temp[currC++] = this.arr[currA++];
            }
        }
        while(currA <= mid) {
            temp[currC++] = this.arr[currA++];
        }
        while(currB <= tail) {
            temp[currC++] = this.arr[currB++];
        }
        // 需要将原数组元素进行覆盖
        System.arraycopy(temp, head, arr, head, tail - head + 1);
    }

    /**
     * 展示归并排序之后数组的元素
     */
    public void display() {
        System.out.print("[");
        for(int i = 0; i < size; i++) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println("]");
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
