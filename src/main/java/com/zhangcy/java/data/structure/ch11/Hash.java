package com.zhangcy.java.data.structure.ch11;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 使用开放地址法完成HASH
 * @author zhangcy
 */
@Slf4j
public class Hash {

    private int[] itemList;

    private int max = 10;

    /**
     * 初始化
     */
    public Hash() {
        itemList = new int[max];
    }

    public void insert(int item) {
        // 先对值进行hash
        int hash = item % max;
        int receive = hash;
        while (itemList[hash] != 0 && itemList[hash] != -1) {
            hash++;
            // 需要及时的使用取模
            // 当两个再次相遇的时候 就表明没有地方可以放了
            hash = hash % max;
            if (hash == receive) {
                throw new RuntimeException("hash table is full");
            }
        }
        itemList[hash] = item;
    }

    public int get(int key) {
        int hash = key % max;
        int receive = hash;
        while(itemList[hash] != key && itemList[hash] != 0) {
            hash++;
            hash = hash % max;
            if(hash == receive) {
                return Integer.MIN_VALUE;
            }
        }
        return itemList[hash];
    }

    public void delete(int key) {
        int hash = key % max;
        int receive = hash;
        while(itemList[hash] != key && itemList[hash] != 0) {
            hash++;
            hash = hash % max;
            if(hash == receive) {
                log.warn("delete key {} false", key);
                return;
            }
        }
        itemList[hash] = -1;
    }

    @Override
    public String toString() {
        return "Hash{" +
                "itemList=" + Arrays.toString(itemList) +
                '}';
    }
}

