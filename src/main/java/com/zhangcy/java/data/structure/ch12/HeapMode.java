package com.zhangcy.java.data.structure.ch12;

import lombok.AllArgsConstructor;

/**
 * 堆的模式 即大碓顶还是小堆顶
 * @author zhangcy
 */
@AllArgsConstructor
public enum HeapMode {

    /**
     * 大碓顶
     */
    MAX(1, "大碓顶"),

    /**
     * 小堆顶
     */
    MIN(2, "小堆顶");

    private int id;

    private String description;
}
