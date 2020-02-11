package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 表示234树的类
 * @author zhangcy
 */
@Slf4j
public class Tree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private Node<T> root;

    /**
     * 在数据搜索指定的元素
     * 假设在查找的过程中一定是有跟元素的
     * 即最少一个节点
     */
    public void find(T data) {
        if(ObjectUtil.isNull(root)) {
            log.info("Tree is empty");
            return;
        }
        // 从根节点开始遍历
        Node<T> currentNode = root;
        // 循环遍历
        while(true) {
            boolean canFind = currentNode.hasFindVal(data);
            // 如果能找到 则提示可以找到
            if(canFind) {
                log.info("find {}", data);
            } else {
                // 判断当前节点是不是叶子节点 如果是直接提示没有找到 否则找应该查询的位置
                if(currentNode.isLeaf()) {
                    log.info("can not find {}", data);
                    break;
                }
                // 判断从那个位置进行查找
                currentNode = currentNode.findNextChild(data);
            }
        }
    }

    /**
     * 向234树中插入元素
     */
    public void insert(T data) {
    }
}
