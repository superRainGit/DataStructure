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
     * 自己编写测试用例
     * 用于校验树内进行旋转的情况
     * 测试通过
     */
    @Test
    public void test8() {
        rbTree.insert(50);
        rbTree.insert(15);
        rbTree.insert(85);
        rbTree.insert(3);
        rbTree.insert(48);
        rbTree.insert(19);
        rbTree.insert(49);
        rbTree.insert(17);
        rbTree.insert(25);
        rbTree.insert(38);
        rbTree.insert(21);
        rbTree.insert(27);
    }

    /**
     * 书上教学测试用例
     * 测试通过
     */
    @Test
    public void test7() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(12);
        rbTree.insert(37);
        rbTree.insert(31);
        rbTree.insert(43);
        rbTree.insert(28);
    }

    /**
     * 书上的测试用例
     * 测试通过
     */
    @Test
    public void test6() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(12);
        rbTree.insert(37);
        rbTree.insert(6);
        rbTree.insert(18);
        rbTree.insert(3);
    }

    /**
     * 测试红黑树从上到下遍历时出现的变色导致的旋转
     * 测试通过
     */
    @Test
    public void test5() {
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(70);
        rbTree.insert(80);
        rbTree.insert(60);
        rbTree.insert(74);
        rbTree.insert(52);
    }

    /**
     * 测试完全有序的节点插入
     * 测试通过
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
     * 测试通过
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
     * 测试通过
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
     * 测试通过
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
     * 测试通过
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
