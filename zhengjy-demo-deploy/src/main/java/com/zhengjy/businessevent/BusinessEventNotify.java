package com.zhengjy.businessevent;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * 事件发送者
 * Created by Jiyang.Zheng on 2019/1/8 15:05.
 */
public class BusinessEventNotify implements ApplicationContextAware {

    private BusinessEventManagement businessEventManagement;

    private ApplicationContext ctx;

    private List<String> businessListenerList;


    public void setBusinessListenerList(List<String> businessListenerList) {
        this.businessListenerList = businessListenerList;
    }



    public void notify(BusinessEvent businessEvent){
        if (businessListenerList.contains(businessEvent.getBusinessEventType())) {
            BusinessEvent event = new BusinessEvent(
                    businessEvent.getBusinessEventType(),
                    businessEvent.getBusinessData());
            ctx.publishEvent(event);
            return;
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
