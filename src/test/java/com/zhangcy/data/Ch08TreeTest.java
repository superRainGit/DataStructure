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
        // 测试树的中序遍历
        System.out.println("中序遍历");
        tree.inOrder();
        // 测试树的先序遍历
        System.out.println("先序遍历");
        tree.preOrder();
        // 测试树的后序遍历
        System.out.println("后序遍历");
        tree.postOrder();
        // 当前树的最小值
        System.out.println(tree.min());
        // 当前树的最大值
        System.out.println(tree.max());
    }
}
