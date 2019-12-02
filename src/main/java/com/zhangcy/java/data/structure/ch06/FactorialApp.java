package com.zhangcy.java.data.structure.ch06;

/**
 * 计算阶乘
 * @author zhangcy
 */
public class FactorialApp {

    /**
     * 计算N的阶乘
     * 书中说的是N的阶乘需要到0才能终止 但是为什么？
     */
    public int factorial(int n) {
        if(n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
}
