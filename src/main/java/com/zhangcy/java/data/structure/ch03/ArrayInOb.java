package com.zhangcy.java.data.structure.ch03;

import com.zhangcy.java.data.structure.ch02.Person;

/**
 * @author zhangcy
 */
public class ArrayInOb {

    private Person[] arr;

    private int size;

    public ArrayInOb(int maxSize) {
        arr = new Person[maxSize];
        size = 0;
    }

    public void insert(Person p) {
        arr[size++] = p;
    }

}
