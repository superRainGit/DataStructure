package com.zhangcy.java.data.structure.ch04;

import java.util.Arrays;
import java.util.List;

/**
 * 中缀表达式 转为后缀表达式
 * @author zhangcy
 */
public class InToPost {

    /**
     * 操作数
     */
    private static final List<Character> dataList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    /**
     * 操作符
     */
    private static final List<Character> operatorList = Arrays.asList('+', '-', '*', '/');

    /**
     * 左括号
     */
    private static final Character leftBracket = '(';

    /**
     * 中缀表达式
     */
    private String middle;

    /**
     * 内部的栈
     */
    private StackX<Character> stackX;

    /**
     * 初始化数据 数据为数据 栈的最大长度为中缀表达式的长度
     */
    public InToPost(String middle) {
        this.middle = middle;
        this.stackX = new StackX<>(middle.length());
    }

    /**
     * 将中缀表达式转为后缀表达式的方法
     * 可以使用switch语句根据读取到的数据进行处理
     */
    public void transfer() {
        System.out.println("Now transfer middle to post --> original: " + this.middle);
        StringBuilder sb = new StringBuilder();
        // 循环读取每一个字符
        for(int i = 0; i < middle.length(); i++) {
            char ch = this.middle.charAt(i);
            if(dataList.contains(ch)) {
                // 如果是操作数 那么需要直接输出
                System.out.println("当前是操作数 直接将操作数进行打印: " + ch);
                sb.append(ch);
            } else if(operatorList.contains(ch)) {
                // 如果是操作符 需要先判断栈中的数据是否为空
                if(this.stackX.isEmpty()) {
                    // 如果栈为空 那么需要将当前的操作符入栈
                    System.out.println("将当前读取到的操作符进行入栈: " + ch);
                    this.stackX.push(ch);
                } else {
                    // 直接出栈
                    while(!this.stackX.isEmpty()) {
                        // 如果栈不为空 先pop出栈顶的元素
                        char top = this.stackX.pop();
                        if(top == leftBracket) {
                            // 如果栈顶的元素是左括号 需要将左括号重新入栈
                            this.stackX.push(top);
                            break;
                        }
                        // 判断当前栈顶取出的元素的运算优先级和读取到的字符的优先级进行比较
                        // 如果当前取出的栈顶的优先级比读取到的运算符的优先级高
                        if(topHighThenRead(ch)) {
                            // 将当前已经出栈的运算符进行打印
                            System.out.println("当前栈顶元素的操作符比目前读入的操作符的优先级高 需要输出栈中的元素: " + top);
                            sb.append(top);
                        } else {
                            // 如果栈顶的元素的优先级比读取到的优先级低
                            // 需要将取出的元素重新的入栈
                            this.stackX.push(top);
                            break;
                        }
                    }
                    // 需要将读取到的操作符入栈
                    System.out.println("当前的栈顶元素的值比读取到的值优先级要高 将当前读取到的操作符进行入栈: " + ch);
                    this.stackX.push(ch);
                }
            } else {
                // 如果读取到的元素是左括号 需要将左括号入栈
                if(ch == leftBracket) {
                    System.out.println("当前读到的字符是左括号, 需要将左括号入栈: " + ch);
                    this.stackX.push(ch);
                } else {
                    // 如果是右括号
                    // 需要依次出栈到左括号
                    while(true) {
                        char top = this.stackX.pop();
                        if(top == leftBracket) {
                            // 读取到了左括号 需要将左括号出栈
                            System.out.println("从右括号向左遍历到了左括号，需要将左括号出栈, 同时结束栈的读取: " + top);
                            break;
                        } else {
                            // 如果不是左括号
                            System.out.println("从右括号向左遍历, 将栈顶元素出栈: " + top);
                            sb.append(top);
                        }
                    }
                }
            }
        }
        // 判断栈中的元素出否还有 如果有需要依次出栈
        while (!this.stackX.isEmpty()) {
            char top = this.stackX.pop();
            System.out.println("数据已经遍历完成 需要把最后的字符依次出栈: " + top);
            sb.append(top);
        }
        System.out.println("middle to post --> result: " + sb.toString());
    }

    /**
     * 判断某一个元素是不是比另一个元素的优先级高
     * 只需要判断读取到的元素是不是+- 因为+-本身的优先级跟自己是相同的
     * @param compare 读取到的操作符
     */
    private boolean topHighThenRead(char compare) {
        // 可以使用ascii码
        return compare == '+' || compare == '-';
    }
}
