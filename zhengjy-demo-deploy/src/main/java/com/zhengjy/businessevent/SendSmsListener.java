package com.zhengjy.businessevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jiyang.Zheng on 2019/1/8 15:19.
 */
public class SendSmsListener implements ApplicationListener {
    final String strKey = "SMS";

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof BusinessEvent) {
            BusinessEvent businessEvent = (BusinessEvent) event;

            if (businessEvent.getBusinessEventType().toUpperCase().contains(strKey)) {
                System.out.println("监听器:" + this + "接收到业务事件，业务事件类型是["
                        + businessEvent.getBusinessEventType()
                        + "] ## 业务事件附带的数据[" + businessEvent.getBusinessData()
                        + "]");
            }
        }
    }
}
