package com.zhangcy.java.data.structure.ch06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 参数和返回值类[其实类似于栈帧]
 * @author zhangcy
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamBean {

    /**
     * 参数
     */
    private int params;

    /**
     * 方法的返回值的位置
     */
    private int returnAddress;
}
