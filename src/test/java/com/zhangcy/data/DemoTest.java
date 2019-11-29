package com.zhangcy.data;

import com.zhangcy.data.structure.LinkList;
import com.zhangcy.data.structure.SqList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DemoTest {

    @Test
    public void testSqList() {
        SqList<String> sqList = new SqList<>(5);
        System.out.println(sqList.size());
        System.out.println(sqList.get(1));
        System.out.println(sqList);
        System.out.println(sqList.size());
        System.out.println(sqList);
        sqList.insert(1, "3");
        sqList.insert(2, "2");
        sqList.insert(3, "5");
        sqList.insert(4, "2");
        System.out.println(sqList);
        System.out.println(sqList.size());
        sqList.insert(1, "4");
        System.out.println(sqList);
        System.out.println(sqList.size());
        sqList.delete(1);
        System.out.println(sqList);
    }

    /**
     * 测试内部的System.arrayCopy
     */
    @Test
    public void testArrayCopy() {
        int[] src = { 1, 2, 3, 0};
        System.out.println(Arrays.stream(src).mapToObj(String::valueOf).collect(Collectors.toList()));
        System.arraycopy(src, 0, src, 1, 3);
        System.out.println(Arrays.stream(src).mapToObj(String::valueOf).collect(Collectors.toList()));
    }

    @Test
    public void testArrayList() {
        List<String> list = new ArrayList<>(5);
        list.add(0, "1");
        list.add(1, "2");
//        list.add(2, "3");
        list.add(3, "4");
        System.out.println(list);
        list.add(0, "5");
        System.out.println(list);
    }

    @Test
    public void testNode() {
        LinkList<String> linkList = new LinkList<>();
//        System.out.println(linkList.size());
//        System.out.println(linkList.get(1));
        linkList.insert(1, "2");
        System.out.println(linkList.get(1));
        System.out.println(linkList.size());
        linkList.insert(1, "3");
        System.out.println(linkList.get(1));
        System.out.println(linkList.size());
    }

}
