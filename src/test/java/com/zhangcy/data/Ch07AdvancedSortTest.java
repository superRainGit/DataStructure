package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch07.*;
import org.junit.Test;

/**
 * 高级排序
 */
public class Ch07AdvancedSortTest {

    /**
     * 测试快速排序 走的判断逻辑是三者之中取适合的中枢元素
     */
    @Test
    public void testQuickSort3() {
        int maxSize = 20;
        QuickSortApp3<Integer> quickSortApp3 = new QuickSortApp3<>(maxSize);
        for(int i = 0; i < maxSize; i++) {
            quickSortApp3.insert((int)(Math.random() * 100));
        }
//        quickSortApp3.insert(36);
//        quickSortApp3.insert(42);
//        quickSortApp3.insert(42);
//        quickSortApp3.insert(54);
//        quickSortApp3.insert(71);
//        quickSortApp3.insert(75);
//        quickSortApp3.insert(79);
        quickSortApp3.display();
        quickSortApp3.quickSort();
        quickSortApp3.display();
    }

    /**
     * 测试去掉右侧边界检查的快速排序
     */
    @Test
    public void testQuickSort2() {
        int maxSize = 20;
        QuickSortApp2<Integer> quickSortApp2 = new QuickSortApp2<>(maxSize);
//        for(int i = 0; i < 5; i++) {
//            quickSortApp2.insert((int)(Math.random() * 100));
//        }
        quickSortApp2.insert(36);
        quickSortApp2.insert(42);
        quickSortApp2.insert(42);
        quickSortApp2.insert(54);
        quickSortApp2.insert(71);
        quickSortApp2.insert(75);
        quickSortApp2.insert(79);
        quickSortApp2.display();
        quickSortApp2.quickSort();
        quickSortApp2.display();
    }

    /**
     * 测试简单版的快速排序
     */
    @Test
    public void testQuickSort() {
        int maxSize = 20;
        QuickSortApp<Integer> quickSortApp = new QuickSortApp<>(maxSize);
        // 42, 93, 6, 53, 57
        // 54, 42, 71, 75, 79
        for(int i = 0; i < 5; i++) {
            quickSortApp.insert((int)(Math.random() * 100));
        }
//        quickSortApp.insert(54);
//        quickSortApp.insert(42);
//        quickSortApp.insert(71);
//        quickSortApp.insert(75);
//        quickSortApp.insert(79);
        quickSortApp.display();
        quickSortApp.quickSort();
        quickSortApp.display();
    }

    /**
     * 测试分组
     */
    @Test
    public void testPartition() {
        int maxSize = 20;
        PartitionApp<Integer> partitionApp = new PartitionApp<>(maxSize);
        partitionApp.insert(49);
        partitionApp.insert(39);
        partitionApp.insert(40);
        partitionApp.insert(40);
        partitionApp.insert(11);
        partitionApp.insert(67);
        partitionApp.insert(96);
        partitionApp.display();
        int partLine = 40;
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
