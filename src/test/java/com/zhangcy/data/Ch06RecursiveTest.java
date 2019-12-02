package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch06.AnagramApp;
import com.zhangcy.java.data.structure.ch06.FactorialApp;
import com.zhangcy.java.data.structure.ch06.TriangleApp;
import org.junit.Test;

/**
 * 测试第六章递归
 */
public class Ch06RecursiveTest {

    /**
     * 测试单词的全排列组合
     */
    @Test
    public void testAnagram() {
        String str = "abc";
        AnagramApp anagramApp = new AnagramApp(str);
        anagramApp.doAnagram(str.length());
    }

    /**
     * 测试阶乘
     */
    @Test
    public void testFactorial() {
        FactorialApp factorialApp = new FactorialApp();
        System.out.println(factorialApp.factorial(5));
    }

    /**
     * 测试三角数字值
     */
    @Test
    public void testTriangle() {
        TriangleApp triangleApp = new TriangleApp();
        System.out.println(triangleApp.triangle(4));
    }
}
