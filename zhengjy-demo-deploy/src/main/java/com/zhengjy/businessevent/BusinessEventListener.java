package com.zhengjy.businessevent;

/**业务事件监听器定义
 * Created by Jiyang.Zheng on 2019/1/8 14:58.
 */
public interface BusinessEventListener {
    /**事件接口*/
    void execute(BusinessEvent event);
}
