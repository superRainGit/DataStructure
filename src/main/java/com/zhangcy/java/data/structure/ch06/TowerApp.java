package com.zhangcy.java.data.structure.ch06;

/**
 * 解决汉诺塔问题
 * 可以将问题分解为如下的解决方式
 * 从A移动N个盘子到C
 *  1、从A移动N-1个盘子到B
 *  2、从A移动第N个盘子到C
 *  3、从B移动N-1个盘子到C
 * @author zhangcy
 */
public class TowerApp {

    /**
     * 盘子的数量
     */
    private int num;

    /**
     * 构造器
     * @param num 初始时盘子的数量
     */
    public TowerApp(int num) {
        this.num = num;
    }

    private void move(int topN, char from, char to) {
        System.out.println("move disk " + topN + " from " + from + " --> " + to);
    }

    /**
     * 移动汉诺塔
     * @param from 从哪个地方开始移动
     * @param inner 中间的中转
     * @param to 最后的节点
     */
    public void moveTower(char from, char inner, char to) {
        doTower(this.num, from, inner, to);
    }

    /**
     * 表明要将topN个盘子从from移动到to
     * @param topN 移动多少个盘子
     * @param from 从哪个地方开始移动
     * @param inner 中间的中转
     * @param to 最后的节点
     */
    private void doTower(int topN, char from, char inner, char to) {
        if(topN == 1) {
            move(topN, from, to);
        } else {
            // 先移动N-1个盘子到中间位置
            doTower(topN - 1, from, to, inner);
            move(topN, from, to);
            // 移动N-1个盘子到最后的位置
            doTower(topN - 1, inner, from, to);
        }
    }
}
