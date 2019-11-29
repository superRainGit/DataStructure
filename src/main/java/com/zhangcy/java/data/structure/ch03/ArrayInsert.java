package com.zhangcy.java.data.structure.ch03;

/**
 * @author zhangcy
 */
public class ArrayInsert {

    private long[] arr;

    private int currIndex;

    public ArrayInsert(int maxSize) {
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
     * 插入排序
     * [5, 3, 4, 6, 8, 11, 18, 15, 7, 9]
     */
    public void insertSort() {
        for(int i = 1; i < currIndex; i++) {
            // 先将当前的数据放入暂存区
            long temp = arr[i];
            int j = i;
            while(j > 0 && arr[j - 1] > temp) {
                // 比较j-1 与temp的值
                // 如果j-1 比temp的值大
                // 需要将当前位置的元素向后移动一位
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    /**
     * 插入排序
     * [5, 3, 4, 6, 8, 11, 18, 15, 7, 9]
     */
    public void insertSortCh35() {
        int compareNum = 0;
        int swapNum = 0;
        for(int i = 1; i < currIndex; i++) {
            // 先将当前的数据放入暂存区
            long temp = arr[i];
            int j = i;
            while(true) {
                compareNum++;
                if(arr[j - 1] > temp) {
                    // 比较j-1 与temp的值
                    // 如果j-1 比temp的值大
                    // 需要将当前位置的元素向后移动一位
                    arr[j] = arr[j - 1];
                    swapNum++;
                } else {
                    break;
                }
                j--;
                if(j <= 0) {
                    break;
                }
            }
            arr[j] = temp;
        }
        System.out.println("compareNum = " + compareNum + ", swapNum = " + swapNum);
    }

    /**
     * 获取中间值
     */
    public long median() {
        this.insertSort();
        return arr[currIndex / 2];
    }

    /**
     * 删除重复的数据
     */
    public void noDup() {
        // 先排序
        this.insertSort();
        // 寻找重复的
    }
}
