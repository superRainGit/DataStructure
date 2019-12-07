package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch05.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Ch05LinkTest {

    /**
     * 测试5.3 和 5.5问题
     */
    @Test
    public void testCh0505() {
        // 人数
        int personNum = 7;
        // 从几开始报数
        int select = 4;
        LoopLinkedList<Integer> loopLinkedList = new LoopLinkedList<>();
        // 数据导入
        for (int i = 1; i <= personNum; i++) {
            loopLinkedList.insert(i);
        }
        loopLinkedList.display();
        // 循环整个循环链表 看出链表的顺序
        while(!loopLinkedList.isEmpty()) {
            // 为什么要到1为止 因为delete删除的是当前元素的下一个元素
            // 因为在单链表中 删除当前节点 如果不循环的话不知道当前链表的上一个元素是什么
            while(select-- != 1) {
                loopLinkedList.step();
            }
            System.out.println(loopLinkedList.delete());
            select = 4;
        }
    }

    /**
     * 测试迭代器
     */
    @Test
    public void testIterator() {
        LinkedListX<String> linkedListX = new LinkedListX<>();
        LinkedListIterator<String> iterator = linkedListX.iterator();
        iterator.insertAfter("1");
        iterator.insertBefore("2");
        linkedListX.display();
    }

    /**
     * 测试双端链表
     */
    @Test
    public void testDoubleLinkNode() {
        // 初始化双端链表
        DoubleLinkedListX<String> doubleLinkedListX = new DoubleLinkedListX<>();
        doubleLinkedListX.insertFirst("2");
        doubleLinkedListX.insertFirst("1");
        doubleLinkedListX.insertFirst("-1");
        doubleLinkedListX.insertFirst("5");
        doubleLinkedListX.display();
        doubleLinkedListX.insertLast("abc");
        doubleLinkedListX.insertLast("def");
        doubleLinkedListX.display();
        doubleLinkedListX.insertAfter("def", "hi");
        doubleLinkedListX.insertAfter("ha", "hei");
        doubleLinkedListX.insertAfter("2", "i3");
        doubleLinkedListX.display();
        while (!doubleLinkedListX.isEmpty()) {
            System.out.println(doubleLinkedListX.deleteLast());
        }
        doubleLinkedListX.display();
        doubleLinkedListX.insertFirst("1");
        doubleLinkedListX.insertFirst("1");
        doubleLinkedListX.insertFirst("1");
        doubleLinkedListX.insertFirst("1");
        doubleLinkedListX.insertFirst("1");
        while (!doubleLinkedListX.isEmpty()) {
            boolean result = doubleLinkedListX.deleteKey("1");
            if(!result) {
                break;
            }
            System.out.println(result);
        }
    }

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
