package com.zhangcy.java.data.structure.ch06;

/**
 * 计算一个数的乘方 x^y
 * 简单的实现，从数学意义上讲将X重复的乘Y次 这样计算的时间复杂度是N
 * 有没有什么时间复杂度比较小的方式呢?
 * 乘方x^y=(x^2)^(y/2)
 * 即使用数学公式的方法去简化计算 使用递归的方式进行计算
 * @author zhangcy
 */
public class PowerApp {

    /**
     * 底数 即X
     */
    private int base;

    /**
     * 幂 即Y
     */
    private int power;

    /**
     * 构造器
     */
    public PowerApp(int base, int power) {
        this.base = base;
        this.power = power;
    }

    /**
     * 计算X^Y次方
     */
    public long power() {
        return recursivePower(this.base, this.power);
    }

    /**
     * 递归的计算乘方
     */
    private long recursivePower(int base, int power) {
        return 0;
    }
}
