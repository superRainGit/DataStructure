package com.zhangcy.java.data.structure.ch06;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 队伍选择APP
 * 在所有的人群中选择指定的人数
 * 求所有满足这种条件的组合
 * 逻辑如下：从M中找N人(M>N)可以简化为
 * 1、选第一个人 以及从(M-1)人中找(N-1)人
 * 2、不选第一个人 从(M-1)人中找N人
 * 可以按照上述的方法进行递归, 什么时候结束呢?
 * 在不能继续分解时就可以结束了
 * 1、参数分解中有一个为0
 * 2、从指定的人数中M挑N人(M<N)时也不需要继续的分解了
 * @author zhangcy
 */
@Slf4j
public class ChooseApp {

    /**
     * 所有的人的组合
     */
    private String[] arr;

    /**
     * 队列的期望长度
     */
    private int combineSize;

    /**
     * 找到的组合数组
     */
    private List<String> combineList;

    /**
     * 构造器
     */
    public ChooseApp(String[] arr, int combineSize) {
        this.arr = arr;
        this.combineSize = combineSize;
        this.combineList = CollUtil.newArrayList();
    }

    /**
     * 列出所有的排列组合
     */
    public void listAll() {
        recursiveList(arr.length, combineSize);
    }

    /**
     * 递归的展示数据
     * @param all 所有的检索数据的个数
     * @param expect 期望的数据的个数集合
     */
    private void recursiveList(int all, int expect) {
        if(all < expect || all == 0 || expect == 0) {
            return;
        }
        // 先取出当前的元素 再说后面的事情
        String currentData = this.arr[this.arr.length - all];
        combineList.add(currentData);
        recursiveList(all - 1, expect - 1);
        // 打印前一个元素的值和当前剩下的值
        if(this.combineList.size() == this.combineSize) {
            log.info("find {} left", this.combineList);
        }
        if(CollUtil.isNotEmpty(combineList)) {
            combineList.remove(combineList.size() - 1);
        }
        recursiveList(all - 1, expect);
    }
}
