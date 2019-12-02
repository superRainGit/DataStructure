package com.zhangcy.java.data.structure.ch06;

/**
 * 变位字【给定一个全英文的字符 找出当前全英文字符的全排列的组合】
 * @author zhangcy
 */
public class AnagramApp {

    /**
     * 全英文字符
     */
    private char[] words;

    /**
     * 构造器
     */
    public AnagramApp(String words) {
        this.words = words.toCharArray();
    }

    /**
     * 对指定的字符求它的全排列的组合
     * 求N的方法
     * 先求右面N-1的的全排列
     * 将当前的字符进行字母的右移
     * 重复上面的步骤N次
     * 当前方法的目的是什么?
     * @param size 以递归的字符的长度作为参数 如果长度为1 那么表明字符串以及走到了最后一个字符
     */
    public void doAnagram(int size) {
        // 先不写终止条件
        if(size == 1) {
            return;
        }
        // 循环N次
        for(int i = 0; i < size; i++) {
            // 处理在首字符之后的N-1个字符
            doAnagram(size - 1);
            // 在轮换之前先打印字符的数据即为一个变位字
            System.out.println(new String(words));
            // 将首字符和之后的N-1个字符进行轮换
            rotate(size);
        }
    }

    /**
     * 从字符串的指定的位置进行轮换
     * @param size 轮换的位置 即从size的位置开始轮换字符串
     */
    private void rotate(int size) {
        // 先取出第一个字符
        char first = words[words.length - size];
        for(int i = words.length - (size - 1); i <= words.length - 1;i++) {
            // 将字符向前移动
            words[i - 1] = words[i];
        }
        words[words.length-1] = first;
    }
}
