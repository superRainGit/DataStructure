package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch07.ShellSort;
import org.junit.Test;

/**
 * 高级排序
 */
public class Ch07AdvancedSortTest {

    /**
     * 测试希尔排序
     */
    @Test
    public void testShellSort() {
        int maxSize = 10;
        ShellSort<Integer> shellSort = new ShellSort<>(maxSize);
        for(int i = 0; i < maxSize; i++) {
            shellSort.insert(maxSize - i);
        }
        shellSort.display();
        shellSort.shellSort();
        shellSort.display();
    }
}
