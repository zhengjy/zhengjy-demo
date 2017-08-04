package com.zhengjy.test.netty.t2;

/**
 * Created by zhengjy on 2017/8/2.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        //因为使用 NIO，所以指定 NioEventLoopGroup 来接受和处理新连接
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //启动服务器应先创建一个 ServerBootstrap 对象
            ServerBootstrap b = new ServerBootstrap();
            //指定通道类型为 NioServerSocketChannel,设置 InetSocketAddress 让服务器监听某个端口已等待客户端连接
            b.group(group).channel(NioServerSocketChannel.class).localAddress(port)
                    //调用 childHandler 放来指定连接后调用的 ChannelHandler
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //Binds server, waits for server to close, and releases resources
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + "started and listen on “" + f.channel(
            ).localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(65535).start();
    }
}