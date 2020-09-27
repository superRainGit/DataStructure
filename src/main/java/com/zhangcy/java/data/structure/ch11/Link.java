package com.zhangcy.java.data.structure.ch11;

import lombok.Getter;
import lombok.Setter;

/**
 * 链表的节点
 * @author zhangcy
 */
@Getter @Setter
public class Link {

    /**
     * 数据项
     */
    private int iData;

    /**
     * 下一个元素的地址 如果没有下一个元素 那么返回的就是null 用于标明节点遍历到什么时候结束
     */
    private Link next;

    public Link(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return this.iData;
    }

    @Override
    public String toString() {
        return iData + "";
    }
}
