package com.zhangcy.java.data.structure.ch06;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的背包问题
 * 给定精准的背包承受质量
 * 在给定的一堆物体中找到符合背包承受重量的物体的集合
 * @author zhangcy
 */
@Slf4j
public class PackApp {

    /**
     * 期望的重量值
     */
    private int expectWeight;

    /**
     * 所有的货物的质量的集合
     */
    private Integer[] allGoods;

    /**
     * 暂时的元素的数组
     */
    private List<Integer> tempGoodList;

    /**
     * 循环的次数
     */
    private int recursiveCount = 0;

    /**
     * 目前数组等待的位置
     */
    private int findIndex;

    /**
     * 构造器
     * @param expectWeight 期望的重量值
     * @param allGoods 所有的货物的质量
     */
    public PackApp(int expectWeight, List<Integer> allGoods) {
        this.expectWeight = expectWeight;
        this.allGoods = allGoods.toArray(new Integer[]{});
        this.tempGoodList = new ArrayList<>();
    }

    /**
     * 在所有的给定的物品中找到重量和为指定重量的物品的集合
     */
    public void find() {
        recursiveFind(this.expectWeight, this.allGoods, 0);
    }

    /**
     * 简单思路
     * 先取数组的第一个元素作为基准值 从左向右找下一个元素 直到找到末尾的元素
     * 先简单的获取第一个满足条件的组合
     */
    public void recursiveFind(int expectWeight, Integer[] allGoods, int fistIndex) {
        // 那么需要方法的返回
        if(recursiveCount == allGoods.length - 1) {
            log.error("Can not find any right");
        } else {
            // 目前的写法是不正确的
            if(fistIndex == this.allGoods.length) {
                // 目前数组等待的位置不是末尾 那么需要从当前的下一个进行找起
                // 数组等待的位置需要前移一位
                if(findIndex != this.allGoods.length - 1) {
                    if(CollUtil.isNotEmpty(this.tempGoodList)) {
                        // 需要先移除最后一个元素的数组
                        this.tempGoodList.remove(this.tempGoodList.size() - 1);
                    }
                    recursiveFind(expectWeight + allGoods[this.findIndex--], allGoods, this.findIndex + 2);
                } else {
                    // 表明尽力了也没有找到 此时应该直接return
                    log.error("Find: {}, but not find perfect!", tempGoodList);
                    // 此时应该将当前的数组进行左移
                    int firstEle = allGoods[0];
                    tempGoodList.clear();
                    System.arraycopy(allGoods, 1, allGoods, 0, allGoods.length - 1);
                    allGoods[allGoods.length - 1] = firstEle;
                    recursiveCount++;
                    // 换完就需要return了 方便进入下面的循环
                    recursiveFind(this.expectWeight, allGoods, 0);
                }
            } else {
                // 每次获取数组剩余的元素中的指定位置元素的值
                int first = allGoods[fistIndex];
                // 如果期望值比这个元素大 那么需要进行递归的查找
                if(expectWeight > first) {
                    log.info("Find: {}, {} It is small than {}!", tempGoodList, first, expectWeight);
                    tempGoodList.add(first);
                    findIndex = fistIndex;
                    recursiveFind(expectWeight - first, allGoods, fistIndex + 1);
                } else if(expectWeight == first) {
                    // 如果期望值正好等于指定位置元素的值
                    // 那么此时已经找到了符合条件的组合
                    // 应该怎么办?
                    // 先直接return
                    log.info("Find: {}, {} It is perfect!", tempGoodList, first);
                    tempGoodList.add(first);
                    tempGoodList.clear();
                    int firstEle = allGoods[0];
                    System.arraycopy(allGoods, 1, allGoods, 0, allGoods.length - 1);
                    allGoods[allGoods.length - 1] = firstEle;
                    recursiveCount++;
                    // 换完就需要return了 方便进入下面的循环
                    recursiveFind(this.expectWeight, allGoods, 0);
                } else {
                    // 如果大于 那么需要摒弃当前的元素 直接找下一个元素
                    log.info("Find: {}, {} It is bigger than {}!", tempGoodList, first, expectWeight);
                    recursiveFind(expectWeight, allGoods, fistIndex + 1);
                }
            }
        }
    }
}
