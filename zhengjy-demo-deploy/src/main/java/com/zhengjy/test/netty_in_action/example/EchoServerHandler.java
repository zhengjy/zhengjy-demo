package com.zhengjy.test.netty_in_action.example;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhengjy on 2017/10/27 16:32
 */
public class EchoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext cxt,Object msg) throws Exception{
        System.out.println("Server received:"+msg);
        cxt.write(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext cxt){
        cxt.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /*
        exceptionCaught 方法可以捕获服务器的异常，比如客户端连接服务器后强制关闭，
        服务器会抛出"客户端主机强制关闭错误"，通过重写 exceptionCaught 方法就可以处理异常，比如发生异常后关闭
        ChannelHandlerContext。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext cxt,Throwable ex){
        ex.printStackTrace();
        cxt.close();
    }


}
