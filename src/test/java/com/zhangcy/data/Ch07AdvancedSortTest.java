package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch07.PartitionApp;
import com.zhangcy.java.data.structure.ch07.ShellSortApp;
import org.junit.Test;

/**
 * 高级排序
 */
public class Ch07AdvancedSortTest {

    /**
     * 测试分组
     */
    @Test
    public void testPartition() {
        int maxSize = 20;
        PartitionApp<Integer> partitionApp = new PartitionApp<>(maxSize);
        partitionApp.insert(49);
        partitionApp.insert(39);
        partitionApp.insert(11);
        partitionApp.insert(67);
        partitionApp.insert(96);
        partitionApp.display();
        int partLine = 100;
        System.out.println("split Line " + partLine);
        partitionApp.partitionIt(partLine);
        partitionApp.display();
    }

    /**
     * 测试希尔排序
     */
    @Test
    public void testShellSort() {
        int maxSize = 10;
        ShellSortApp<Integer> shellSortApp = new ShellSortApp<>(maxSize);
        for(int i = 0; i < maxSize; i++) {
            shellSortApp.insert(maxSize - i);
        }
        shellSortApp.display();
        shellSortApp.shellSort();
        shellSortApp.display();
    }
}
