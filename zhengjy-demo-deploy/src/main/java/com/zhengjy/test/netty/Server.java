package com.zhengjy.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by zhengjy on 2017/3/25.
 */
public class Server {
    public static void main(String[] args) throws InterruptedException {
        //启动服务器应先创建一个 ServerBootstrap 对象
        ServerBootstrap bootstrap = new ServerBootstrap();

        //1 第一个线程组是用于接受客户端连接的
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        //2 第二个线程组是用于实际业务处理操作的
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //把两个工作线程组加进来
        bootstrap.group(boosGroup,workerGroup)
                //我们要指定NioServerSocketChannel这种类型的通道
                .channel(NioServerSocketChannel.class)
                //调用 childHandler 放来指定连接后调用的 ChannelHandler，这个方法传 ChannelInitializer 类型的参数，
                // ChannelInitializer 是个抽象类，所以需要实现 initChannel 方法，这个方法就是用来设置 ChannelHandler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                })
                //
                .option(ChannelOption.SO_BACKLOG,128)
                //设置一个tcp连接缓冲区
                .childOption(ChannelOption.SO_KEEPALIVE,true);
        //绑定指定的端口，进行监听，调用 sync()方法会阻塞直到服务器完成绑定，然后服务器等待通道关闭， 因为使用 sync()，所以关闭操作也会被阻塞。
        ChannelFuture f = bootstrap.bind(8765).sync();
        f.channel().closeFuture().sync();
        boosGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
