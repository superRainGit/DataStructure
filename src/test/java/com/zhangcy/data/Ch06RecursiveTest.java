package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch06.*;
import org.junit.Test;

/**
 * 测试第六章递归
 */
public class Ch06RecursiveTest {

    /**
     * 测试汉诺塔问题
     */
    @Test
    public void testTower() {
        int num = 3;
        TowerApp towerApp = new TowerApp(num);
        towerApp.moveTower('A', 'B', 'C');
    }

    /**
     * 测试递归的二分查找
     */
    @Test
    public void testBinarySearch() {
        int maxSize = 5;
        BinarySearchApp<Integer> searchApp = new BinarySearchApp<>(maxSize);
        searchApp.insert(2);
        searchApp.insert(1);
        searchApp.insert(3);
        searchApp.insert(-1);
        searchApp.insert(5);
        searchApp.display();
        System.out.println(searchApp.find(4));
    }

    /**
     * 测试单词的全排列组合
     */
    @Test
    public void testAnagram() {
        String str = "abcd";
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
