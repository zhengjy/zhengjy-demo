package com.zhengjy.test.jmx;

import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Created by zhengjy on 2017/5/23.
 */
public class HelloAgent {
    public static void main(String[] args) throws JMException, Exception
    {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);
        Thread.sleep(60*60*1000);
    }
}
