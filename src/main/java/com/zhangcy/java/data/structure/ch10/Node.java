package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 234树中的节点
 * 234树中的节点包含数据项、子节点项
 * @author zhangcy
 */
@Slf4j
public class Node<T extends Comparable<T>> {

    /**
     * 节点中的数据项
     */
    List<DataItem<T>> dataItemList;

    /**
     * 节点中的子节点项
     */
    List<Node<T>> nodeList;

    /**
     * 当前节点中数据项的个数
     */
    int dataSize;

    /**
     * 当前节点的父节点的地址
     */
    Node<T> parentNode;

    /**
     * 判断是否是叶子节点
     */
    private boolean isLeaf() {
        return CollUtil.isEmpty(this.nodeList);
    }

    /**
     * 判断当前节点是否为满数据项
     */
    private boolean isFull() {
        return CollUtil.isNotEmpty(this.dataItemList) && this.dataItemList.size() == 3;
    }

    /**
     * 打印当前节点中的元素
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DataItem<T> tDataItem : dataItemList) {
            sb.append(tDataItem);
        }
        sb.append(StrUtil.SLASH);
        return sb.toString();
    }
}
