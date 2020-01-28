package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch09.RbTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Ch09RbTreeTest {

    public RbTree<Integer> rbTree;

    /**
     * 测试方法之前进行初始化
     */
    @Before
    public void before() {
        rbTree = new RbTree<>();
    }

    /**
     * 测试完成之后进行红黑树的打印工作
     */
    @After
    public void after() {
        rbTree.display();
    }

    /**
     * 测试完全有序的节点插入
     */
    @Test
    public void test4() {
        rbTree.insert(1);
        rbTree.insert(2);
        rbTree.insert(3);
        rbTree.insert(4);
        rbTree.insert(5);
        rbTree.insert(6);
        rbTree.insert(7);
        rbTree.insert(8);
    }

    /**
     * 测试左内联节点
     */
    @Test
    public void test3() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(12);
        rbTree.insert(18);
    }

    /**
     * 测试变色
     */
    @Test
    public void test2() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(12);
    }

    /**
     * 测试三个已经排好序的左外联节点
     */
    @Test
    public void testSortedThreeNode() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(12);
    }

    /**
     * 测试只插入一个节点
     * 即跟节点的一棵树
     */
    @Test
    public void testRootNode() {
        rbTree.insert(1);
    }

    /**
     * 测试红黑树
     */
    @Test
    public void testRbTree() {
        RbTree<Integer> rbTree = new RbTree<>();
        rbTree.insert(50);
        rbTree.insert(25);
//        rbTree.insert(75);
//        rbTree.insert(45);
//        rbTree.insert(30);
        rbTree.insert(12);
        rbTree.insert(6);
//        rbTree.insert(30);
//        rbTree.insert(90);
//        rbTree.insert(70);
        rbTree.display();
    }
}
