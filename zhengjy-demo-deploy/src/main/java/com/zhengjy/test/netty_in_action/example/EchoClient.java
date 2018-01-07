//package com.zhengjy.test.netty_in_action.example;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//
//import java.net.InetSocketAddress;
//
///**
// * Created by zhengjy on 2017/10/27 17:15
// */
//public class EchoClient {
//
//    public static void main(String[] args) throws InterruptedException {
//        //创建 EventLoopGroup 对象并设置到 Bootstrap 中，EventLoopGroup 可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
//        EventLoopGroup group = new NioEventLoopGroup();
//        //创建 Bootstrap 对象用来引导启动客户端
//        Bootstrap b = new Bootstrap();
//        b.group(group).channel(NioServerSocketChannel.class).
//                //创建 InetSocketAddress 并设置到 Bootstrap 中，InetSocketAddress 是指定连接的服务器地址
//                remoteAddress(new InetSocketAddress("127.0.0.1",7777)).
//                handler(new ChannelInitializer<SocketChannel>() {
//
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        //添加一个 ChannelHandler，客户端成功连接服务器后就会被执行
//                        ch.pipeline().addLast(new EchoClientHandler());
//                    }
//                });
//        //调用 Bootstrap.connect()来连接服务器
//        ChannelFuture f = b.connect().sync();
//        f.channel().closeFuture().sync();
//        //最后关闭 EventLoopGroup 来释放资源
//        group.shutdownGracefully().sync();
//
//    }
//}
