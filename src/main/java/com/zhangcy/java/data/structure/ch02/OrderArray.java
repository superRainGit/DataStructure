package com.zhangcy.java.data.structure.ch02;

/**
 * @author zhangcy
 */
public class OrderArray {

    private long[] arr;

    private int nEle;

    public OrderArray(int size) {
        arr = new long[size];
        nEle = 0;
    }

    public int size() {
        return nEle;
    }

    public long get(int index) {
        return arr[index];
    }

    /**
     * 插入元素
     */
    public void insert(long insertKey) {
        int i;
        for(i = 0; i < nEle; i++) {
            if(arr[i] > insertKey) {
                break;
            }
        }
        nEle++;
        // 慎用System.arrayCopy()
        if(nEle != i + 1) {
            System.arraycopy(arr, i, arr, i + 1, nEle - i - 1);
        }
        arr[i] = insertKey;
    }

    /**
     * 填充数据
     */
    public void fill() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            nEle++;
        }
    }

    /**
     * 使用二分查找数据
     */
    public int find(long searchKey) {
        int i = 0; int j = nEle - 1;
        int curr;
        if(arr[i] > searchKey || arr[j] < searchKey ) {
            return -1;
        }
        while(true) {
            curr = (i + j) / 2;
            if(arr[curr] == searchKey) {
                return curr;
            } else if(i > j) {
                return -1;
            } else {
                if(searchKey > arr[curr]) {
                    i = curr + 1;
                } else {
                    j = curr - 1;
                }
            }
       }
    }

    public void display() {
        for (int i = 0; i < nEle; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 合并两个原本有序的数组
     * [1, 3, 4, 7, 11]
     * [2, 7, 8, 9, 13, 15, 18]
     */
    public OrderArray merge(OrderArray src2) {
        OrderArray orderArray = new OrderArray(this.size() + src2.size());
        int i = 0;
        int j = 0;
        for (; i < this.size();) {
            for (; j < src2.size();) {
                if(this.get(i) < src2.get(j)) {
                    orderArray.insert(this.get(i));
                    i++;
                    break;
                } else {
                    orderArray.insert(src2.get(j));
                    j++;
                }
            }
        }
        if(i < this.size()) {
            for(; i < this.size(); i++) {
                orderArray.insert(this.get(i));
            }
        } else if(j < src2.size()) {
            for(; j < src2.size(); j++) {
                orderArray.insert(src2.get(j));
            }
        }
        return orderArray;
    }
}
