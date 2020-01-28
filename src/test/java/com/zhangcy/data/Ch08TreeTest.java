package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch08.Tree;
import org.junit.Test;

/**
 * 树的单元测试
 */
public class Ch08TreeTest {

    /**
     * 测试删除线索二叉树的元素
     */
    @Test
    public void testDeleteBST() {
        Tree<Integer> tree = new Tree<>();
        tree.insert(22);
        tree.insert(19);
        tree.insert(17);
        tree.insert(21);
        tree.insert(8);
        tree.insert(4);
        tree.insert(16);
        tree.insert(15);
        tree.insert(12);
        tree.insert(13);
        tree.insert(76);
        tree.insert(45);
        tree.insert(88);
        tree.insert(32);
        tree.insert(70);
        // 测试树的中序遍历
        System.out.println("中序遍历");
        tree.inOrder();
        // 删除叶子节点元素
        tree.delete(22);
        tree.delete(32);
        tree.inOrder();
    }

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
