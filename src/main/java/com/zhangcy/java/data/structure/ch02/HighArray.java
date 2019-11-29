package com.zhangcy.java.data.structure.ch02;

/**
 * @author zhangcy
 */
public class HighArray {

    private long[] arr;

    private int nEle;

    /**
     * 初始化数组
     */
    public HighArray(int size) {
        arr = new long[size];
        nEle = 0;
    }

    /**
     * 插入
     */
    public void insert(long ele) {
        arr[nEle] = ele;
        nEle++;
    }

    public int size() {
        return nEle;
    }

    /**
     * 查找
     */
    public boolean find(long searchKey) {
        for (int i = 0; i < nEle; i++) {
            if(arr[i] == searchKey) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除
     */
    public boolean delete(long deleteKey) {
        int i = 0;
        for (; i < nEle; i++) {
            if(arr[i] == deleteKey) {
                break;
            }
        }
        if(i == nEle) {
            return false;
        } else {
            System.arraycopy(arr, i + 1, arr, i, nEle - i - 1);
            nEle--;
            return true;
        }
    }

    /**
     * 展示数组
     */
    public void display() {
        for (int i = 0; i < nEle; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public boolean removeMax() {
        boolean result = false;
        if(nEle != 0) {
            long max = arr[0];
            int i = 1;
            for (; i < nEle; i++) {
                if(max < arr[i]) {
                    max = arr[i];
                }
            }
            result = delete(max);
        }
        return result;
    }

    public long getMax() {
        long max = -1;
        if(nEle != 0) {
            for (int i = 0; i < nEle; i++) {
                if(max < arr[i]) {
                    max = arr[i];
                }
            }
        }
        return max;
    }

    /**
     * 删除重复的元素
     */
    public void noDup() {

    }
}
