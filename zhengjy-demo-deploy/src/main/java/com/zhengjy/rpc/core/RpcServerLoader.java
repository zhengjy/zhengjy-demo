package com.zhengjy.rpc.core;

import com.zhengjy.rpc.serialize.RpcSerializeProtocol;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**rpc服务器配置加载
 * Created by Jiyang.Zheng on 2019/1/11 13:39.
 */
public class RpcServerLoader {

    private volatile static RpcServerLoader rpcServerLoader;
    private final static String DELIMITER = ":";

    private RpcSerializeProtocol serializeProtocol = RpcSerializeProtocol.JDKSERIALIZE;
    //返回java虚拟机的可用处理器数量
    private final static int parallel = Runtime.getRuntime().availableProcessors() * 2;
    //netty nio线程池
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);

    private static ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16,-1);

    private MessageSendHandler messageSendHandler = null;

    //等待netty服务端链路建立通知信号
    private Lock lock = new ReentrantLock();
    private Condition signal = lock.newCondition();

    private RpcServerLoader(){
    }

    public static RpcServerLoader getInstance(){
        return Instance.rpcServerLoader;
    }

    public void load(String serverAddress) {
        String[] ipAddr = serverAddress.split(RpcServerLoader.DELIMITER);
        if (ipAddr.length == 2){
            String host = ipAddr[0];
            Integer port = Integer.parseInt(ipAddr[1]);
            InetSocketAddress socket = new InetSocketAddress(host,port);
            poolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup,socket,this,serializeProtocol));
        }
    }

    public void sendMessageHandle(MessageSendHandler messageSendHandler){
        try{
            lock.lock();
            this.messageSendHandler = messageSendHandler;
            //唤醒所有等待客户端RPC线程
            signal.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public MessageSendHandler getMessageSendHandler(){
        try {
            lock.lock();
            if (messageSendHandler == null){
                try {
                    signal.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return messageSendHandler;
        }finally {
            lock.unlock();
        }
    }


    public void unload() {
        messageSendHandler.close();
        poolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();

    }


    private static class Instance{
        private  static RpcServerLoader rpcServerLoader = new RpcServerLoader();


    }
}
