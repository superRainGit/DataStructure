package com.zhangcy.java.data.structure.ch06;

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
     * 查找的次数
     */
    private int findCount = 0;

    /**
     * 目前查找的数组的左边索引
     */
    private int leftIndex;

    /**
     * 目前查找的数组的右边索引
     */
    private int rightIndex;

    /**
     * tempList里面最后一个元素的位置
     * 有可能是跨数组的索引的
     */
    private int lastIndex;

    /**
     * 构造器
     * @param expectWeight 期望的重量值
     * @param allGoods 所有的货物的质量
     */
    public PackApp(int expectWeight, List<Integer> allGoods) {
        this.expectWeight = expectWeight;
        this.allGoods = allGoods.toArray(new Integer[]{});
        this.tempGoodList = new ArrayList<>();
        this.leftIndex = 0;
        this.rightIndex = 0;
        this.lastIndex = 0;
    }

    /**
     * 在所有的给定的物品中找到重量和为指定重量的物品的集合
     */
    public void find() {
        recursiveFind(this.expectWeight, this.allGoods);
    }

    /**
     * 简单思路
     * 先取数组的第一个元素作为基准值 从左向右找下一个元素 直到找到末尾的元素
     * 先简单的获取第一个满足条件的组合
     * leftIndex tempList参考的第一个元素在数组的位置
     * rightIndex tempList参考的最后一个元素数组的位置
     * lastIndex 组成tempList数组的最后一个元素的位置
     */
    public void recursiveFind(int expectWeight, Integer[] allGoods) {
        this.findCount++;
        if(this.leftIndex != allGoods.length) {
            // 如果已经找到了最后一个元素 那么判断lastIndex的位置
            if(this.rightIndex == allGoods.length) {
                if(this.lastIndex == allGoods.length) {
                    // 如果已经走到了最右面的元素 那么表明已经没有必要找了
                    // 需要将左边元素的位置移动一下
                    this.leftIndex++;
                    this.rightIndex = this.leftIndex;
                    this.lastIndex = this.leftIndex;
                    this.tempGoodList.clear();
//                    log.info("now find the last one, I need a new last begin");
                    recursiveFind(this.expectWeight, allGoods);
                } else {
                    // 如果不是最后一个 那么移动一下lastIndex
                    this.rightIndex = this.lastIndex;
                    // 取出最后的那个元素
                    int lastOne =this.tempGoodList.remove(this.tempGoodList.size() - 1);
//                    log.info("now find the last one, I need a new begin");
                    recursiveFind(expectWeight + lastOne, allGoods);
                }
            } else {
                // 取出目前最右边的获取到的元素
                int current = allGoods[this.rightIndex];
                if(current < expectWeight) {
//                    log.info("find {}, expect {}, but {} is smaller", tempGoodList, expectWeight, current);
                    // 如果当前元素比期望值小 那么需要将当前的元素加入到备用列表中
                    this.tempGoodList.add(current);
                    this.rightIndex++;
                    this.lastIndex++;
                    // 直接接入下次的递归
                    recursiveFind(expectWeight - current, allGoods);
                } else if(current > expectWeight) {
                    // 如果当前元素比期望值大 那么需要直接进行下次的递归
                    this.rightIndex++;
//                    log.info("find {}, expect {}, but {} is bigger", tempGoodList, expectWeight, current);
                    recursiveFind(expectWeight, allGoods);
                } else {
                    log.info("find {}, {} is very perfect", tempGoodList, current);
                    // 如果正好等于 直接打印满足条件的组合
                    this.tempGoodList.add(current);
                    // 再去找其他的元素去
                    this.rightIndex++;
                    this.tempGoodList.remove(this.tempGoodList.size() - 1);
                    recursiveFind(expectWeight, allGoods);
                }
            }
        } else {
            log.info("find all the combine");
        }
    }

    public void display() {
        log.error("Finally, I find {} times", this.findCount);
    }
}
