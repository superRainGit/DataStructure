package com.zhangcy.java.data.structure.ch06;

/**
 * 变位字【给定一个全英文的字符 找出当前全英文字符的全排列的组合】
 * 计算字符串的全排列的方法
 * 计算N个字符的全排列可以采用如下的方法
 * 1 计算N的字符的全排列 等价于 计算第N个字符+第N-1个字符的全排列
 * 2 将之前计算的第N个字符算数左移到字符串的右边
 * 3 重复的计算上面的逻辑N次 因为有N个字符 每次移动都需要重新计算上面的逻辑 所以是N次循环
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
     * @param size 以递归的字符的长度作为参数 如果长度为1 那么表明字符串已经走到了最后一个字符
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
            // 为何要在 size == 2时进行数据的输出呢?
            // 因为整理之后可以发现 无论计算多长的字符串的全排列 每次进入方法之后
            // 就会因为计算子串的全排列而进入递归 最后只有在子串长度为2时会发生数据的交换
            // 如果要是不判断的话 会有一个问题就是 经过交换后的字符串会变为原来的顺序 导致重复打印
            if(size == 2) {
                System.out.println(new String(words));
            }
            // 将首字符和之后的N-1个字符进行轮换
            rotate(size);
        }
    }

    /**
     * 从字符串的指定的位置进行轮换
     * @param size 轮换的位置 即从size的位置开始轮换字符串 这个size是从末尾数起的
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
