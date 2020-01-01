package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch08.Tree;
import org.junit.Test;

/**
 * 树的单元测试
 */
public class Ch08TreeTest {

    /**
     * 测试线索二叉树
     */
    @Test
    public void testBST() {
        Tree<Integer> tree = new Tree<>();
        tree.insert(19);
        tree.insert(17);
        tree.insert(15);
        tree.insert(22);
        tree.insert(16);
        tree.find(22);
        tree.inOrder();
    }
}
