package com.zhangcy.java.data.structure.ch04;

import lombok.extern.slf4j.Slf4j;

/**
 * 计算后缀表达式的结果
 * @author zhangcy
 */
@Slf4j
public class ParsePost {

    /**
     * 内部维护操作数的栈
     */
    private StackX<Integer> stackX;

    /**
     * 后缀表达式
     */
    private String post;

    /**
     * 后缀表达式的长度
     */
    private int postLen;

    public ParsePost(String post) {
        this.post = post;
        this.postLen = post.length();
        this.stackX = new StackX<>(postLen);
    }

    /**
     * 计算后缀表达式的结果
     * 这次采用switch的写法
     */
    public void doParse() {
        int op1;
        int op2;
        int result;
        for(int i = 0; i < this.postLen; i++) {
            // 读取字符
            char ch = this.post.charAt(i);
            switch (ch) {
                case '+':
                    // 如果是操作符 那么需要弹出栈顶的两个元素进行计算 然后入栈
                    op1 = this.stackX.pop();
                    op2 = this.stackX.pop();
                    result = op1 + op2;
                    this.stackX.push(result);
                    log.info("读取到+操作符 弹出栈顶的数据: " + op1 + ", " + op2);
                    break;
                case '-':
                    // 如果是操作符 那么需要弹出栈顶的两个元素进行计算 然后入栈
                    op1 = this.stackX.pop();
                    op2 = this.stackX.pop();
                    result = op2 - op1;
                    this.stackX.push(result);
                    log.info("读取到-操作符 弹出栈顶的数据: " + op1 + ", " + op2);
                    break;
                case '*':
                    // 如果是操作符 那么需要弹出栈顶的两个元素进行计算 然后入栈
                    op1 = this.stackX.pop();
                    op2 = this.stackX.pop();
                    result = op1 * op2;
                    this.stackX.push(result);
                    log.info("读取到*操作符 弹出栈顶的数据: " + op1 + ", " + op2);
                    break;
                case '/':
                    // 如果是操作符 那么需要弹出栈顶的两个元素进行计算 然后入栈
                    op1 = this.stackX.pop();
                    op2 = this.stackX.pop();
                    result = op2 / op1;
                    this.stackX.push(result);
                    log.info("读取到/操作符 弹出栈顶的数据: " + op1 + ", " + op2);
                    break;
                default:
                    // 如果是操作数 那么需要将操作数入栈
                    this.stackX.push(Character.digit(ch, 10));
                    log.info("读取到操作数 压入栈顶的数据: " + ch);
                    break;
            }
        }
        log.info("后缀表达式的计算结果: " + this.stackX.pop());
    }
}
