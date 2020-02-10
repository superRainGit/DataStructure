package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 234树的数据项
 * @author zhangcy
 */
@Slf4j
public class DataItem<T extends Comparable<T>> {

    /**
     * 数据项中的数据
     */
    private T data;

    /**
     * 构造器 用户产生含有指定数据的数据项
     * @param data 初始数据
     */
    public DataItem(T data) {
        this.data = data;
    }

    /**
     * 展示数据项中的数据
     */
    @Override
    public String toString() {
        return StrUtil.SLASH.concat(data.toString());
    }
}
