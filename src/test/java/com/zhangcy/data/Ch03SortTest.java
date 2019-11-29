package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch03.ArrayBub;
import com.zhangcy.java.data.structure.ch03.ArrayInsert;
import com.zhangcy.java.data.structure.ch03.ArraySel;
import org.junit.Test;

public class Ch03SortTest {

    @Test
    public void testInsertSort() {
        int maxSize = 8;
        ArrayInsert arrayInsert = new ArrayInsert(maxSize);
        // [5, 3, 4, 6, 8, 11, 18, 15, 7, 9]

        // 10W个随机数据 1秒
//        for (int i = 0; i < maxSize; i++) {
//            arrayInsert.insert((long)(Math.random() * (maxSize - 1)));
//        }

        // 10W个逆序数据 // 1.9秒
//        for (int i = 0; i < maxSize; i++) {
//            arrayInsert.insert(maxSize - i);
//        }

        // 10W个有序数据 // 0.002秒
        for (int i = 0; i < maxSize; i++) {
            arrayInsert.insert(i);
        }
//        arrayInsert.insert(5);
//        arrayInsert.insert(3);
//        arrayInsert.insert(4);
//        arrayInsert.insert(6);
//        arrayInsert.insert(8);
//        arrayInsert.insert(11);
//        arrayInsert.insert(18);
//        arrayInsert.insert(15);
//        arrayInsert.insert(7);
//        arrayInsert.insert(9);

        arrayInsert.display();

//        long start = System.currentTimeMillis();

        arrayInsert.insertSortCh35();

//        long end = System.currentTimeMillis();

//        System.out.println("cost " + (end - start) / 1000d );

        arrayInsert.display();
    }

    @Test
    public void testSelectSort() {
        int maxSize = 100000;
        ArraySel arraySel = new ArraySel(maxSize);

        // 10W个随机数据 3秒
//        for (int i = 0; i < maxSize; i++) {
//            arraySel.insert((long)(Math.random() * (maxSize - 1)));
//        }

        // 10W个逆序数据 // 3.6秒
//        for (int i = 0; i < maxSize; i++) {
//            arraySel.insert(maxSize - i);
//        }

        // 10W个顺序数据 // 1.418秒
        for (int i = 0; i < maxSize; i++) {
            arraySel.insert(i);
        }


        // [1, 4, 3, 9, 11, 18, 15, 14]
//        arraySel.insert(1);
//        arraySel.insert(4);
//        arraySel.insert(3);
//        arraySel.insert(9);
//        arraySel.insert(11);
//        arraySel.insert(18);
//        arraySel.insert(15);
//        arraySel.insert(14);

//        arraySel.display();

        long start = System.currentTimeMillis();

        arraySel.selectSort();

        long end = System.currentTimeMillis();

        System.out.println("cost " + (end - start) / 1000d );

//        arraySel.display();
    }

    @Test
    public void testBubbleSort() {
        int maxSize = 20;
        ArrayBub arrayBub = new ArrayBub(maxSize);
        // [3, 4, 1, 5, 7, 8, 11, 9]
        arrayBub.insert(3);
        arrayBub.insert(4);
        arrayBub.insert(1);
        arrayBub.insert(5);
        arrayBub.insert(7);
        arrayBub.insert(8);
        arrayBub.insert(17);
        arrayBub.insert(9);
        arrayBub.insert(11);

        arrayBub.display();
        // 10W个随机数据 15秒
//        for (int i = 0; i < maxSize; i++) {
//            arrayBub.insert((long)(Math.random() * (maxSize - 1)));
//        }

        // 10W个逆序数据 // 3秒
//        for (int i = 0; i < maxSize; i++) {
//            arrayBub.insert(maxSize - i);
//        }

        // 10W个顺序数据 // 1.472秒
//        for (int i = 0; i < maxSize; i++) {
//            arrayBub.insert(i);
//        }

//        long start = System.currentTimeMillis();

//        arrayBub.bubbleSort();

        arrayBub.bubbleSort2();

//        long end = System.currentTimeMillis();
//
//        System.out.println("cost " + (end - start) / 1000d );

        arrayBub.display();
    }

    @Test
    public void testOddEvenSort() {
        int maxSize = 20;
        ArrayBub arrayBub = new ArrayBub(maxSize);
        // [3, 4, 1, 5, 7, 8, 11, 9]
        arrayBub.insert(3);
        arrayBub.insert(4);
        arrayBub.insert(1);
        arrayBub.insert(5);
        arrayBub.insert(7);
        arrayBub.insert(8);
        arrayBub.insert(17);
        arrayBub.insert(9);
        arrayBub.insert(11);

        arrayBub.display();

        arrayBub.oddEvenSort();

        arrayBub.display();
    }
}
