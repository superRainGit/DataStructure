package com.zhangcy.java.data.structure.ch11;

import java.util.LinkedList;

/**
 * 链地址法
 * @author zhangcy
 */
public class HashChain {

    private LinkedList<DataItem>[] dataItems;

    private int hashLen;

    public HashChain() {
        this(16);
    }

    public HashChain(int hashLen) {
        assert hashLen > 0;
        this.hashLen = hashLen;
        dataItems = new LinkedList[hashLen];
    }

    /**
     * 插入数据
     */
    public void insert(int data) {
        // 先hash计算结果
        int hash = data % hashLen;
        // 找到对应的数组
    }
}
