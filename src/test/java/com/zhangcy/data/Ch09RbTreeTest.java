package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch09.RbTree;
import org.junit.Test;

public class Ch09RbTreeTest {

    /**
     * 测试红黑树
     */
    @Test
    public void testRbTree() {
        RbTree<Integer> rbTree = new RbTree<>();
        rbTree.insert(50);
        rbTree.insert(25);
        rbTree.insert(75);
        rbTree.insert(12);
        rbTree.insert(20);
        rbTree.insert(90);
        rbTree.insert(85);
        rbTree.display();
    }
}
