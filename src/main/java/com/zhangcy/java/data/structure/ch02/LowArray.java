package com.zhangcy.java.data.structure.ch02;

/**
 * @author zhangcy
 */
public class LowArray {

    private long[] arr;

    public LowArray(int size) {
        arr = new long[size];
    }

    public long getEle(int index) {
        return arr[index];
    }

    public void setEle(int index, long value) {
        arr[index] = value;
    }
}
