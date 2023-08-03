package com.zhangcy.java.data.structure.jmx;

/**
 * 服务信息MBean(JMX Managed Bean)
 * @author zhangcy
 */
public interface ServerInfoMBean {

    /**
     * 查询执行sql的数量
     * @return 执行sql的数量
     */
    int getExecutedSqlCmd();

    /**
     * 设置执行sql的数量
     * @param executedSqlCmd sql执行的数量
     */
    void setExecutedSqlCmd(int executedSqlCmd);

    /**
     * 设置执行sql的数量
     * @param executedSqlCmd sql执行的数量
     */
    void refreshEx(int executedSqlCmd);

    /**
     * 打印日志
     * @param fromConsole 控制台字符串
     */
    void printString(String fromConsole);
}
