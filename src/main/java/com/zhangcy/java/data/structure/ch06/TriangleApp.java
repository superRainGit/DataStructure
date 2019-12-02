package com.zhangcy.java.data.structure.ch06;

/**
 * 三角数据值
 * 计算形如 1 3 6 10 15 21
 * @author zhangcy
 */
public class TriangleApp {

    /**
     * 计算第N项的数据
     */
    public int triangle(int n) {
        if(n == 1) {
            return 1;
        }
        return triangle(n - 1) + n;
    }
}
