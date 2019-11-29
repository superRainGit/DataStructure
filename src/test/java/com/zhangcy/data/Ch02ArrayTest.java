package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch02.*;
import org.junit.Test;

import java.util.Random;

public class Ch02ArrayTest {

    @Test
    public void testMerge() {
        OrderArray o1 = new OrderArray(10);

        // [1, 3, 4, 7, 11]
        // [2, 7, 8, 9, 13, 15, 18]
        o1.insert(1);
        o1.insert(3);
        o1.insert(4);
        o1.insert(7);
        o1.insert(11);

        OrderArray o2 = new OrderArray(10);
        o2.insert(2);
        o2.insert(7);
        o2.insert(8);
        o2.insert(9);
        o2.insert(13);
        o2.insert(15);
        o2.insert(18);

        o1.display();
        o2.display();
        o1.merge(o2).display();

    }

    @Test
    public void testClassDataArray() {
        int maxSize = 20;
        ClassDataArray classDataArray = new ClassDataArray(maxSize);
        classDataArray.inset(new Person("Evans", "Patty", 24));
        classDataArray.inset(new Person("Smith", "Lorraine", 37));
        classDataArray.inset(new Person("Yee", "Tom", 43));
        classDataArray.inset(new Person("Adams", "Henry", 63));
        classDataArray.inset(new Person("Hashimoto", "Sato", 21));
        classDataArray.inset(new Person("Stimson", "Henry", 29));
        classDataArray.inset(new Person("Velasquez", "Jose", 72));
        classDataArray.inset(new Person("Lamarque", "Henry", 54));
        classDataArray.inset(new Person("Vang", "Minh", 22));
        classDataArray.inset(new Person("Creswell", "Lucinda", 18));

        classDataArray.display();
        String searchKey = "Stimson";
        Person found;
        found = classDataArray.find(searchKey);
        if(found != null) {
            System.out.print("Found ");
            found.displayPerson();
        } else {
            System.out.println("Can't find " + searchKey);
        }

        System.out.println("Deleting Smith, Yee, and Creswell");
        classDataArray.delete("Smith");
        classDataArray.delete("Yee");
        classDataArray.delete("Creswell");

        classDataArray.display();
    }

    @Test
    public void testBinaryArray() {
        OrderArray orderArray = new OrderArray(10);
        orderArray.insert(77);
        orderArray.insert(99);
        orderArray.insert(44);
        orderArray.insert(55);
        orderArray.insert(22);
        orderArray.insert(88);
        orderArray.insert(11);
        orderArray.insert(0);
        System.out.println(orderArray.find(9));
        orderArray.display();
    }

    @Test
    public void testHighArray() {
        int size = 10;
        HighArray highArray = new HighArray(size);
        highArray.insert(25);
        highArray.insert(3);
        highArray.insert(1);
        highArray.insert(2);
        highArray.insert(8);
        highArray.insert(9);
        highArray.insert(14);
        highArray.insert(21);
        highArray.insert(15);
        highArray.insert(5);
        highArray.display();
        int search = 25;
        if(highArray.find(search)) {
            System.out.println("Found " + search);
        } else {
            System.out.println("Can't find " + search);
        }

        highArray.delete(9);
        highArray.delete(15);
        highArray.delete(14);

        highArray.display();
//        System.out.println(highArray.getMax());
//        highArray.removeMax();
//        highArray.display();

        HighArray newHigh = new HighArray(size);
        int arrSize = highArray.size();
        for (int i = 0; i < arrSize; i++) {
            newHigh.insert(highArray.getMax());
            highArray.removeMax();
        }
        newHigh.display();
    }

    @Test
    public void testLowArray() {
        Random r = new Random();
        LowArray arr;
        arr = new LowArray(10);
        int j;
        int nElements = 0;
        for (int i = 0; i < 10; i++) {
            arr.setEle(i, r.nextInt(27));
            nElements ++;
        }
        // display
        for (j = 0; j < nElements; j++) {
            System.out.println("index[" + j  + "]="+ arr.getEle(j));
        }
        // search
        int searchKey = 26;
        for (j = 0; j < nElements; j++) {
            if(arr.getEle(j) == searchKey) {
                break;
            }
        }
        if(nElements == j) {
            System.out.println("do not search " + searchKey);
        } else {
            System.out.println("search " + searchKey);
        }
        // delete
        int deleteKey = 25;
        for(j = 0; j < nElements; j++) {
            if(arr.getEle(j) == deleteKey) {
                break;
            }
        }
        if(j == nElements) {
            System.out.println("do not search " + deleteKey);
        } else {
            for (int k = j; k < nElements - 1; k++) {
                arr.setEle(k, arr.getEle(k + 1));
            }
            nElements--;
        }
        // display
        for (j = 0; j < nElements; j++) {
            System.out.println("index[" + j  + "]="+ arr.getEle(j));
        }
    }
}
