package com.zhangcy.java.data.structure.ch06;

/**
 * 使用栈实现递归的原理
 * 使用一个栈的方式去实现三角形数据
 * @author zhangcy
 */
public class StackTriangleApp {

    /**
     * 参数和返回值的栈
     */
    private StackApp<ParamBean> paramStack;

    /**
     * 计算第N项三角数据的值
     */
    private int num;

    /**
     * 用于计算步进的步骤编号
     */
    private int stepNum = 1;

    /**
     * 构造器 用于初始化计算三角数据的第N项的值
     * @param num 指定需要计算的第N项
     */
    public StackTriangleApp(int num) {
        this.num = num;
    }

    /**
     * 模拟递归的进行运算
     */
    public void recTriangle() {
        while(!this.step()) {
            ;
        }
    }

    /**
     * 步进递归的计算三角数据的值
     * @return 如果该方法返回true 那么表明已经计算完成 可以返回数据了
     */
    public boolean step() {
        switch (stepNum) {
            // 表明刚开始计算
            case 1:
                break;
            default:
                break;
        }
        return false;
    }

}
