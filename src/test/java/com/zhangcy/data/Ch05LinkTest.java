package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch05.FirstLastLinkedList;
import com.zhangcy.java.data.structure.ch05.LinkedListX;
import com.zhangcy.java.data.structure.ch05.SortedListX;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Ch05LinkTest {

    /**
     * 实现插入排序 通过有序链表的方式
     */
    @Test
    public void testInsertSortBySortList() {
        Integer[] arr = new Integer[10];
        SortedListX<Integer> sortedListX = new SortedListX<>();
        for (int i = 0; i < 10; i++) {
            arr[i] = (int)(Math.random() * 100);
        }
        System.out.print("unsorted arr: ");
        for (int i = 0; i <10 ; i++) {
            // 将数据插入到有序链表中
            sortedListX.insert(arr[i]);
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.print("  sorted arr: ");
        // 将有序数组输出
        for (int i = 0; i <10 ; i++) {
            System.out.print(sortedListX.removeHead() + " ");
        }
    }

    /**
     * 测试有序列表
     */
    @Test
    public void testSortList() {
        SortedListX<Integer> sortedListX = new SortedListX<>();
        sortedListX.insert(1);
        sortedListX.insert(4);
        sortedListX.insert(2);
        sortedListX.insert(3);
        sortedListX.display();
        sortedListX.remove(4);
        sortedListX.display();
        sortedListX.insert(-1);
        sortedListX.display();
    }

    /**
     * 测试如果没有将尾指针改为null会oom
     * 且对应的尾结点的元素指向的数据并没有被释放
     */
    @Test
    public void testFLListOOM() {
        List<FirstLastLinkedList<OOMObject>> linkedListList = new ArrayList<>();
        // 看什么时候OOM
        while(true) {
            FirstLastLinkedList<OOMObject> lastLinkedList = new FirstLastLinkedList<>();
            lastLinkedList.insertLast(new OOMObject());
            lastLinkedList.deleteHead();
            linkedListList.add(lastLinkedList);
        }
    }

    static class OOMObject {
        private byte[] b;

        public OOMObject() {
            b = new byte[1024];
        }
    }

    /**
     * 简单测试双端链表
     */
    @Test
    public void testFirstLastLinkedList() {
        FirstLastLinkedList<String> lastLinkedList = new FirstLastLinkedList<>();
        lastLinkedList.insertLast("1");
        lastLinkedList.display();
        lastLinkedList.insertLast("4");
        lastLinkedList.display();
        // 移除头结点
        System.out.println(lastLinkedList.deleteHead());
        System.out.println(lastLinkedList.deleteHead());
        lastLinkedList.display();
        lastLinkedList.insertHead("3");
        lastLinkedList.insertLast("2");
        lastLinkedList.display();
    }

    /**
     * 测试单链表的查找和删除
     */
    @Test
    public void testLinkSD() {
        LinkedListX<String> linkedListX = new LinkedListX<>();
        log.info("find 1 ? {}", linkedListX.searchData("1"));
        linkedListX.insertHead("1");
        log.info("find 1 ? {}", linkedListX.searchData("1"));
        linkedListX.insertHead("2");
        linkedListX.insertHead("3");
        linkedListX.display();
        log.info("delete 4 ? {}", linkedListX.deleteData("4"));
        linkedListX.display();
        linkedListX.insertHead("4");
        linkedListX.insertHead("5");
        log.info("delete 4 ? {}", linkedListX.deleteData("4"));
        linkedListX.display();
        linkedListX.deleteData("5");
        linkedListX.display();
        linkedListX.deleteData("3");
        linkedListX.deleteData("2");
        linkedListX.deleteData("1");
        linkedListX.deleteData("1");
        linkedListX.display();
    }

    /**
     * 测试简单的单链表的插入和移除
     */
    @Test
    public void testLinkList() {
        LinkedListX<String> linkedListX = new LinkedListX<>();
        linkedListX.insertHead("1");
        linkedListX.insertHead("2");
        linkedListX.insertHead("3");
        linkedListX.insertHead("4");
        linkedListX.insertHead("5");
        linkedListX.insertHead("6");
        linkedListX.insertHead("7");
        linkedListX.display();
        log.info(linkedListX.removeHead());
        log.info(linkedListX.removeHead());
        log.info(linkedListX.removeHead());
        linkedListX.display();
    }
}
