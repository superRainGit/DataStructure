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
     * 记录最后的计算结果
     */
    private int result;

    /**
     * 当前处理的参数的值
     */
    private int currNum;

    /**
     * 构造器 用于初始化计算三角数据的第N项的值
     * @param num 指定需要计算的第N项
     */
    public StackTriangleApp(int num) {
        this.num = num;
        this.currNum = num;
        // 栈的长度让他是递归的次数+2
        this.paramStack = new StackApp<>(num + 2);
    }

    /**
     * 模拟递归的进行运算
     */
    public int recTriangle() {
        while(!this.step()) {
            // 空的方法用于模拟递归的调用过程
        }
        return this.result;
    }

    /**
     * 步进循环的计算三角数据的值
     * @return 如果该方法返回true 那么表明已经计算完成 可以返回数据了
     */
    private boolean step() {
        switch (stepNum) {
            case 1:
                // 表明刚开始计算
                // 需要将当前调用的方法对象直接入栈 此时的数据的返回值是方法的调用方 默认用-1吧
                ParamBean first = new ParamBean(0, -1);
                paramStack.push(first);
                // 同时引导进入第二步
                stepNum = 2;
                break;
            case 2:
                // 判断遇到终止条件进行方法的返回 ? 那么什么时候开始进行数据的返回呢？ 在递归里面的数据是在遇到1的时候进行的返回
                // 先瞄一眼栈顶的元素的数据
                ParamBean top = paramStack.peek();
                // 如果参数值是1 表明现在已经遇到了出栈的时机了
                if(top.getParams() == 1) {
                    this.result = 1;
                    stepNum = top.getReturnAddress();
                    // 需要将栈顶的元素出栈
                    paramStack.pop();
                } else {
                    // 如果目前栈顶的元素的参数不是1
                    stepNum = 3;
                }
                break;
            case 3:
                // 需要将当前的参数和返回位置进行入栈
                // 参数可以获取 那么返回的值呢？
                // 返回的参数先写个0 到返回的时候决定应该往哪里走
                ParamBean next = new ParamBean(this.currNum--, 0);
                paramStack.push(next);
                // 返回到步骤2进行递归
                stepNum = 2;
                break;
            case 0:
                // 遇到0 那么开始进行计算
                // 取出栈顶的元素
                ParamBean calTop = paramStack.pop();
                // 进行数据的计算
                this.result = this.result + calTop.getParams();
                stepNum = calTop.getReturnAddress();
                break;
            case -1:
                // 如果遇到-1 那边表示是时候返回结果了
                return true;
            default:
                throw new IllegalArgumentException("stack is over flow");
        }
        return false;
    }

}
