package com.zhangcy.java.data.structure.ch10;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 234树的数据项
 * @author zhangcy
 */
@Slf4j
public class DataItem<T extends Comparable<T>> {

    /**
     * 数据项中的数据
     */
    @Getter
    private T data;

    /**
     * 构造器 用户产生含有指定数据的数据项
     * @param data 初始数据
     */
    public DataItem(T data) {
        this.data = data;
    }

    /**
     * 重写equals方法
     * 主要是为了list的contains方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DataItem<?> dataItem = (DataItem<?>) o;
        return Objects.equals(data, dataItem.data);
    }

    /**
     * 重写hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    /**
     * 展示数据项中的数据
     */
    @Override
    public String toString() {
        return StrUtil.SLASH.concat(data.toString());
    }
}
