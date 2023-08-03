package com.zhangcy.java.data.structure.jmx;

/**
 * serviceInfo实现类
 * @author zhangcy
 */
public class ServerInfo implements ServerInfoMBean {

    private int executedSqlCmd;

    @Override
    public int getExecutedSqlCmd() {
        System.out.println("get executed sql cmd " + this.executedSqlCmd);
        return this.executedSqlCmd;
    }

    @Override
    public void setExecutedSqlCmd(int executedSqlCmd) {
        System.out.println("set executed sql cmd " + executedSqlCmd);
        this.executedSqlCmd = executedSqlCmd;
    }

    @Override
    public void refreshEx(int executedSqlCmd) {
        System.out.println("refreshEx executed sql cmd " + executedSqlCmd);
        this.executedSqlCmd = executedSqlCmd;
    }

    @Override
    public void printString(String fromConsole) {
        System.out.println("print string " + fromConsole);
    }
}
