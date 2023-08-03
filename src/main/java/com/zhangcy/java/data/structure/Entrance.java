package com.zhangcy.java.data.structure;

import com.zhangcy.java.data.structure.jmx.ServerInfo;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * 入口
 * @author zhangcy
 */
public class Entrance {

    public static void main(String[] args) throws JMException, InterruptedException {
        jmxStart();
        TimeUnit.SECONDS.sleep(200);
    }

    /**
     * 启用jmx
     */
    private static void jmxStart() throws JMException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("serverInfoMBean:name=serverInfo");
        mBeanServer.registerMBean(new ServerInfo(), objectName);
    }
}
