package com.zhengjy.businessevent;

import org.springframework.context.ApplicationEvent;

/**
 * 业务事件定义
 * Created by Jiyang.Zheng on 2019/1/8 14:56.
 */
public class BusinessEvent extends ApplicationEvent {
    /**某种具体的业务事件的数据内容*/
    private Object businessData;
    /**某种具体的业务事件的事件类型*/
    private String businessEventType;

    public BusinessEvent(String businessEventType,Object businessData){
        super(businessEventType);
        this.businessData = businessData;
        this.businessEventType = businessEventType;
    }

    public Object getBusinessData() {
        return businessData;
    }

    public String getBusinessEventType() {
        return businessEventType;
    }
}
