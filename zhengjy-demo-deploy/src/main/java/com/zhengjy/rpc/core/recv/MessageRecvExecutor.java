package com.zhengjy.rpc.core.recv;

import com.zhengjy.rpc.core.MessageSendChannelInitializer;
import com.zhengjy.rpc.core.NamedThreadFactory;
import com.zhengjy.rpc.core.RpcThreadPool;
import com.zhengjy.rpc.model.MessageKeyVal;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**Rpc服务器执行模块
 * Created by Jiyang.Zheng on 2019/1/11 15:21.
 */
public class MessageRecvExecutor implements ApplicationContextAware,InitializingBean {
    private String serverAddress;
    private final static String DELIMITER = ":";

    private Map<String,Object> handlerMap = new ConcurrentHashMap<>();

    private static ThreadPoolExecutor threadPoolExecutor;

    public MessageRecvExecutor(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public static void submit(Runnable task){
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        //netty的线程池模型设置成主从线程池模式
//        ThreadFactory threadRpcFactory = new NamedThreadFactory("NettyRPC ThreadFactory");
//        //方法返回到Java虚拟机的可用的处理器数量
//        int parallel = Runtime.getRuntime().availableProcessors() * 2;
//
//        EventLoopGroup boss = new NioEventLoopGroup();
//        EventLoopGroup worker = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap bootstrap = new ServerBootstrap();
//            bootstrap.group(boss,worker).channel(NioServerSocketChannel.class)
//                    .childHandler(new MessageRecvChannelInitializer(handlerMap))
//                    .option(ChannelOption.SO_BACKLOG, 128)
//                    .childOption(ChannelOption.SO_KEEPALIVE, true);
//            String[] ipAddr = serverAddress.split(MessageRecvExecutor.DELIMITER);
//
//            if (ipAddr.length == 2) {
//                String host = ipAddr[0];
//                int port = Integer.parseInt(ipAddr[1]);
//                ChannelFuture future = bootstrap.bind(host, port).sync();
//                System.out.printf("[author tangjie] Netty RPC Server start success ip:%s port:%d\n", host, port);
//                future.channel().closeFuture().sync();
//            } else {
//                System.out.printf("[author tangjie] Netty RPC Server start fail!\n");
//            }
//
//        }catch (Exception e){
//            worker.shutdownGracefully();
//            boss.shutdownGracefully();
//        }

    }

    @Override
    public void setApplicationContext(ApplicationContext cxt) throws BeansException {
        try {

            MessageKeyVal keyVal = (MessageKeyVal) cxt.getBean(Class.forName("com.zhengjy.rpc.model.MessageKeyVal"));
            Map<String,Object> rpcServiceObject = keyVal.getMessageKeyVal();
            rpcServiceObject.forEach((k,v)-> handlerMap.put(k,v));


        }catch (Exception e){

        }

    }
}
