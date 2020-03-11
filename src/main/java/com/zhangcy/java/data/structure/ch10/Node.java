package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
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
    List<DataItem<T>> dataItemList = CollUtil.newArrayList();

    /**
     * 节点中的子节点项
     */
    List<Node<T>> nodeList = CollUtil.newArrayList();

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
    public boolean isLeaf() {
        return CollUtil.isEmpty(this.nodeList);
    }

    /**
     * 判断当前节点是否为满数据项
     */
    public boolean isFull() {
        return CollUtil.isNotEmpty(this.dataItemList) && this.dataItemList.size() == 3;
    }

    /**
     * 判断能否从当前节点的数据域中找到想要查找的元素
     */
    public boolean hasFindVal(T data) {
        return CollUtil.isNotEmpty(this.dataItemList) && this.dataItemList.contains(new DataItem<>(data));
    }

    /**
     * 获取当前节点的中间数据
     * 因为获取的时候数据节点是满的 所以可以直接获取
     */
    public T getMiddleData() {
        return this.dataItemList.get(1).getData();
    }

    /**
     * 获取当前节点的末位数据
     * 因为获取的时候数据节点是满的 所以可以直接获取
     */
    public T getLastData() {
        return this.dataItemList.get(2).getData();
    }

    /**
     * 获取当前节点的初位数据
     * 因为获取的时候数据节点是满的 所以可以直接获取
     */
    public T getFirstData() {
        return this.dataItemList.get(0).getData();
    }

    /**
     * 因为234树的特殊性质:
     *  1个数据元素的节点有两个分叉
     *  2个数据元素的节点有三个分叉
     *  3个数据元素的节点有四个分叉
     * 所以判断要查找的元素和当前节点的数据元素之前的关系 然后获取指定位置的元素
     * 查找需要向哪个方向查询数据
     */
    public Node<T> findNextChild(T data) {
        int i = 0;
        for(; i < dataItemList.size(); i++) {
            // 如果比左边的元素小 那么直接从左边开始查找
            if(data.compareTo(dataItemList.get(i).getData()) < 0) {
                break;
            }
        }
        return nodeList.get(i);
    }

    /**
     * 将新增的数据放入到节点中
     */
    public void insertNewData2Node(T data) {
        this.dataSize++;
        DataItem<T> newData = new DataItem<>(data);
        this.dataItemList.add(newData);
        CollUtil.sort(dataItemList, Comparator.comparing(DataItem::getData));
    }

    /**
     * 移除中间元素
     */
    public void removeMiddleData() {
        this.dataSize--;
        this.dataItemList.remove(1);
    }

    /**
     * 移除中间元素
     */
    public void removeLastData() {
        this.dataSize--;
        this.dataItemList.remove(2);
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
