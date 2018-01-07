//package com.zhengjy.test.netty_in_action.example;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
///**
// * Created by zhengjy on 2017/10/27 16:25
// */
//public class EchoServer {
//
//    public static void main(String[] args) throws InterruptedException {
//        //启动服务应先创建一个ServerBootstrap
//        ServerBootstrap b = new ServerBootstrap();
//        //因为使用NIO，所以指定NioEventLoopGroup来接收和处理新连接
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        //指定通道类型为NioServerSocketChannel,
//        b.group(group).channel(NioServerSocketChannel.class).
//                //设置InetSocketAddress让服务器监听某个端口已等待客户端连接
//                localAddress(7777).
//                //调用childHandler放来指定连接后调用的ChannelHandler，这个方法传入ChannelInitializer类型的参数，
//                //ChannelInitializer是个抽象类，所以需要实现InitChannel方法，这个方法就是用来设置ChannlerHandler
//                childHandler(new ChannelInitializer<Channel>() {
//            @Override
//            protected void initChannel(Channel ch) throws Exception {
//                 ch.pipeline().addLast(new EchoServerHandler());
//            }
//        });
//        //调用 sync()方法会阻塞直到服务器完成绑定，然后服务器等待通道关闭，因为使用 sync()，所以关闭操作也会被阻塞
//        ChannelFuture f = b.bind().sync();
//
//        System.out.println(EchoServer.class.getName() + "started  and  listen  on  “"+ f.channel().localAddress());
//
//        f.channel().closeFuture().sync();
//
//        group.shutdownGracefully().sync();
//
//    }
//}
