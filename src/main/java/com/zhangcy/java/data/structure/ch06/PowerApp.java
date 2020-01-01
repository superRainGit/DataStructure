package com.zhangcy.java.data.structure.ch06;

import lombok.extern.slf4j.Slf4j;

/**
 * 计算一个数的乘方 x^y
 * 简单的实现，从数学意义上讲将X重复的乘Y次 这样计算的时间复杂度是N
 * 有没有什么时间复杂度比较小的方式呢?
 * 乘方x^y=(x^2)^(y/2)
 * 即使用数学公式的方法去简化计算 使用递归的方式进行计算
 * @author zhangcy
 */
@Slf4j
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
     * 其实就是递归的计算(X^2)^(y/2)
     */
    public long power() {
        long result = recursivePower(this.base, this.power);
        log.info("power: {} ^ {} = {}", this.base, this.power, result);
        return result;
    }

    /**
     * 递归的计算乘方
     */
    private long recursivePower(int base, int power) {
        // 先考虑幂是2的次幂的情况
        // 先将幂除以2
        // 如果是2的N次幂 那么很好计算
        // 如果无法整除2 那么如何计算呢
        // 例如计算 2 ^ 3 = (2*2)^(3/2) 不难发现 当最后无法除以2时 就需要额外的乘当前的一个base 即额外的底数
        // 在计算额为的底数乘法时 需要在每次无法除尽时做当时底数的乘法
        // 先计算整数整除
        int halfPower = power / 2;
        // 计算余数
        int remainPower = power % 2;
        if(halfPower == 1) {
            if(remainPower == 0) {
                log.info("now cal the last one {} * {} = {}", base, base, base * base);
            } else {
                log.info("now cal the last one {} * {} * {} = {}", base, base, base, base * base * base);
            }
            // 不好意思 偷了个懒 直接用数学公式的x^0=1
            return base * base * (int)(Math.pow(base, remainPower));
        }
        if(remainPower == 0) {
            log.info("recursive power ({}^2)^({})", base, halfPower);
        } else {
            log.info("recursive power ({}^2)^({}) * {}", base, halfPower, base);
        }
        return recursivePower(base * base, halfPower) * (int)(Math.pow(base, remainPower));
    }
}
