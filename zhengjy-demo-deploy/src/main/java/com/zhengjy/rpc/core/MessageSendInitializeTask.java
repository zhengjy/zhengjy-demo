package com.zhengjy.rpc.core;

import com.zhengjy.rpc.serialize.RpcSerializeProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**Rpc客户端线程任务处理
 * Created by Jiyang.Zheng on 2019/1/11 14:18.
 */
public class MessageSendInitializeTask implements Runnable {
    private EventLoopGroup eventLoopGroup;
    private InetSocketAddress inetSocketAddress;
    private RpcServerLoader rpcServerLoader;
    private RpcSerializeProtocol rpcSerializeProtocol;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress inetSocketAddress, RpcServerLoader rpcServerLoader, RpcSerializeProtocol rpcSerializeProtocol) {
        this.eventLoopGroup = eventLoopGroup;
        this.inetSocketAddress = inetSocketAddress;
        this.rpcServerLoader = rpcServerLoader;
        this.rpcSerializeProtocol = rpcSerializeProtocol;
    }



    @Override
    public void run() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.handler(new MessageSendChannelInitializer());

        ChannelFuture channelFuture = bootstrap.connect(inetSocketAddress);
        channelFuture.addListener((ChannelFuture c) ->{
            if (c.isSuccess()){
                MessageSendHandler handler = c.channel().pipeline().get(MessageSendHandler.class);
                MessageSendInitializeTask.this.rpcServerLoader.sendMessageHandle(handler);
            }
        });


    }
}
