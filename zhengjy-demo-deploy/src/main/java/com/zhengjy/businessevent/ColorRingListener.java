package com.zhengjy.businessevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jiyang.Zheng on 2019/1/8 15:41.
 */
public class ColorRingListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof BusinessEvent) {
            BusinessEvent businessEvent = (BusinessEvent) event;
            System.out.println("监听器:" + this + "接收到业务事件，业务事件类型是["
                    + businessEvent.getBusinessEventType() + "] ## 业务事件附带的数据["
                    + businessEvent.getBusinessData() + "]");
        }
    }
}
