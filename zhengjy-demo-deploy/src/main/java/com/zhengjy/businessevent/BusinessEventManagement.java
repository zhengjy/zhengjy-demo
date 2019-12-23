package com.zhengjy.businessevent;


import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务事件管理器定义
 * Created by Jiyang.Zheng on 2019/1/8 15:00.
 */
public class BusinessEventManagement {

    private Map<String,List<BusinessEventListener>> map = new HashMap<>();

    /**
     * 注册事件监听器
     * @param businessEventType
     * @param listener
     */
    public void addBusinessEventListener(String businessEventType,BusinessEventListener listener){
        List<BusinessEventListener> list = map.get(businessEventType);
        if (list == null){
            list = Lists.newArrayList();
        }
        list.add(listener);
        map.put(businessEventType,list);
    }

    /**
     * 移除业务事件监听器
      */
    public boolean removeBusinessEventListener(String BusinessEventType,BusinessEventListener listener) {
        List<BusinessEventListener> listeners = map.get(BusinessEventType);

        if (null != listeners) {
            return listeners.remove(listener);
        }

        return false;
    }


    public List<BusinessEventListener> getBusinessEventListener(String businessEventType){
        return map.get(businessEventType);
    }


}
