package com.zhengjy.businessevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 在模块初始化的时候，注册若干观察者，然后它们处理自己感兴趣的内容。
 * 当某一个具体的事件发生的时候，遍历观察者队列，然后”观察者“们就根据之前约定的具体情况，处理自己关注的事件。
 * Created by Jiyang.Zheng on 2019/1/8 15:42.
 */
public class Main {

    public static void main(String[] args) {
//        BusinessEventManagement management = new BusinessEventManagement();
//        management.addBusinessEventListener("彩铃监听器",new ColorRingListener());
//        management.addBusinessEventListener("短信监听器",new SendSmsListener());
//
//        BusinessEventNotify sender = new BusinessEventNotify(management);
//
//        sender.notify(new BusinessEvent("ReadBusinessEvent","彩铃监听器"));
//        sender.notify(new BusinessEvent("WriteBusinessEvent","短信监听器"));

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-config.xml");

        BusinessEventNotify sender = (BusinessEventNotify) ctx.getBean("event");

        sender.notify(new BusinessEvent("DealColorRing", "ReadBusinessEvent"));

        sender.notify(new BusinessEvent("DealSms", "ReadBusinessEvent"));

    }
}
