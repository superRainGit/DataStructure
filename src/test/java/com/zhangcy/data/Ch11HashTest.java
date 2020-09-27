package com.zhangcy.data;

import com.zhangcy.java.data.structure.ch11.Hash;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Ch11HashTest {

    @Test
    public void testHash() {
        Hash hash = new Hash();
        hash.insert(100);
        System.out.println(hash);
        hash.insert(101);
        System.out.println(hash);
        hash.insert(102);
        System.out.println(hash);
        hash.delete(101);
        System.out.println(hash);
        hash.insert(200);
        System.out.println(hash);
        hash.insert(104);
        System.out.println(hash);
        hash.insert(300);
        System.out.println(hash);
        hash.insert(400);
        hash.insert(500);
        hash.insert(700);
        hash.insert(900);
        hash.insert(800);
        hash.delete(900);
        hash.insert(1000);
        System.out.println(hash);
    }
}
