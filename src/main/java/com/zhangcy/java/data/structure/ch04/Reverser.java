package com.zhangcy.java.data.structure.ch04;

/**
 * @author zhangcy
 */
public class Reverser {

    /**
     * 输入
     */
    private String input;

    public Reverser(String input) {
        this.input = input;
    }

    public String doReverse() {
        int len = input.length();
        StackX stackx = new StackX(len);
        for (int i = 0; i < len; i++) {
            stackx.push(input.charAt(i));
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < len; i++) {
            out.append(stackx.pop());
        }
        return out.toString();
    }
}
