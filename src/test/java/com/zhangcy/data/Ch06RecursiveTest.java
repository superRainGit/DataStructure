package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch06.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 测试第六章递归
 */
public class Ch06RecursiveTest {

    /**
     * 测试背包问题
     */
    @Test
    public void testPack() {
        int expectWeight = 100;
        List<Integer> arr = Arrays.asList(11, 8, 7, 6, 5, 9);
        PackApp packApp = new PackApp(expectWeight, arr);
        packApp.find();
    }

    /**
     * 测试递归的计算X的Y次幂的结果
     */
    @Test
    public void testPower() {
        int base = 2;
        int power = 9;
        PowerApp powerApp = new PowerApp(base, power);
        powerApp.power();
    }

    /**
     * 测试使用栈的方式代替实现递归
     */
    @Test
    public void testStackRecursive() {
        int num = 5;
        StackTriangleApp app = new StackTriangleApp(num);
        System.out.println(app.recTriangle());
    }

    /**
     * 测试归并排序
     */
    @Test
    public void testMerge() {
        int maxSize = 20;
        Integer[] arrA = {23, 47, 81, 95};
        Integer[] arrB = {7, 14, 39, 55, 62, 74};
        MergeApp<Integer> mergeApp = new MergeApp<>(maxSize);
        Arrays.asList(mergeApp.merge(arrA, arrB)).forEach(System.out::println);
        mergeApp.insert(23);
        mergeApp.insert(47);
        mergeApp.insert(81);
        mergeApp.insert(95);
        mergeApp.insert(7);
        mergeApp.insert(14);
        mergeApp.insert(39);
        mergeApp.insert(55);
        mergeApp.insert(62);
        mergeApp.insert(74);
        mergeApp.display();
        mergeApp.mergeSort();
        mergeApp.display();
    }

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
