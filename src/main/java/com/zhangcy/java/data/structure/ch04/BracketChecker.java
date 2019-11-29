package com.zhangcy.java.data.structure.ch04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字符校验
 * @author zhangcy
 */
public class BracketChecker {

    /**
     * 左字符
     */
    private static final List<Character> left = Arrays.asList('{', '[', '(');

    /**
     * 右字符
     */
    private static final List<Character> right = Arrays.asList('}', ']', ')');

    /**
     * 左边符号的map
     */
    private static Map<Character, Character> leftMap = new HashMap<>();

    /**
     * 右边符号的map
     */
    private static Map<Character, Character> rightMap = new HashMap<>();

    static {
        leftMap.put('{', '}');
        leftMap.put('[', ']');
        leftMap.put('(', ')');

        rightMap.put('}', '{');
        rightMap.put(']', '[');
        rightMap.put(')', '(');
    }

    /**
     * 输入
     */
    private String input;

    public BracketChecker(String input) {
        this.input = input;
    }

    public boolean isCorrect() {
        int len = input.length();
        // 判断是否是个正确的字符串
        StackX<Character> stack = new StackX<>(len);
        // 先取字符
        for(int i = 0; i < len; i++) {
            // 获取当前取到的字符
            char ch = input.charAt(i);
            // 如果是右字符 直接弹第一个栈里面的数据
            if(right.contains(ch)) {
                // 看一下第一个元素
                // 如果没有元素 那么直接表示有问题
                if(stack.isEmpty()) {
                    return false;
                } else {
                    // 取出右边对应
                    char peek = stack.pop();
                    if(peek == rightMap.get(ch)) {
                        continue;
                    }
                    return false;
                }
            } else if(left.contains(ch)) {
                // 如果是左字符 直接入栈
                stack.push(ch);
            }
        }
        // 最后判断如果仍有字符在栈中 表明有问题
        return stack.isEmpty();
    }
}
