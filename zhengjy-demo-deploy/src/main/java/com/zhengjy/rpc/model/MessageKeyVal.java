package com.zhengjy.rpc.model;

import java.util.Map;

/**
 *
 * rpc服务映射容器
 * Created by Jiyang.Zheng on 2019/1/11 11:30.
 */
public class MessageKeyVal {

    private Map<String,Object> messageKeyVal;

    public void setMessageKeyVal(Map<String, Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }
}
