package com.zhengjy.rpc.core;

import com.zhengjy.rpc.model.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**rpc客户端消息处理
 * MessageSendProxy其实是把消息发送给RpcServerLoader模块
 * Created by Jiyang.Zheng on 2019/1/11 13:48.
 */
public class MessageSendProxy<T> implements InvocationHandler {

    private Class<T> clazz;

    public MessageSendProxy(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setMethodName(method.getName());
        request.setClassName(method.getDeclaringClass().getName());
        request.setTypeParamters(method.getParameterTypes());
        request.setParamnetersVal(args);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack call = handler.sendRequest(request);

        return call.start();
    }
}
