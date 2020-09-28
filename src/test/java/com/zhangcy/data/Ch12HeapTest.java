package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch12.Heap;
import org.junit.Test;

public class Ch12HeapTest {

    @Test
    public void testInsert() {
        Heap heap = new Heap();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        System.out.println(heap);
        heap.remove();
        System.out.println(heap);
    }
}
