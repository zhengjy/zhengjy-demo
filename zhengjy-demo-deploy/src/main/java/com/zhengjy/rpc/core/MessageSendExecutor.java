package com.zhengjy.rpc.core;

import java.lang.reflect.Proxy;

/**
 * 客户端执行模块
 * Created by Jiyang.Zheng on 2019/1/11 13:38.
 */
public class MessageSendExecutor {

    private RpcServerLoader loader = RpcServerLoader.getInstance();


    public MessageSendExecutor(String serverAddress){
        loader.load(serverAddress);
    }

    public void stop(){
        loader.unload();
    }

    public static <T> T execute(Class<T> rpcInterface){
        return (T) Proxy.newProxyInstance(rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface));
    }

}
