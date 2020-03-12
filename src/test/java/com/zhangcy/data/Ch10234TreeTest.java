package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch10.Tree;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试234树
 */
public class Ch10234TreeTest {

    private Tree<Integer> tree;

    @Before
    public void before() {
        tree = new Tree<>();
    }

    /**
     * 测试根节点的插入和打印
     */
    @Test
    public void testNodeInsert() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(10);
        tree.insert(11);
        tree.insert(12);
        tree.insert(13);
        tree.insert(14);
        tree.display();
    }
}
