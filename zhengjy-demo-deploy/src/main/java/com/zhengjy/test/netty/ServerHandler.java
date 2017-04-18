package com.zhengjy.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;


/**
 * Created by zhengjy on 2017/3/25.
 */
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
//        ((ByteBuf)msg).release();
        try{
            ByteBuf buf = ((ByteBuf)msg);
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            String request = new String(data,"utf-8");
            System.out.println("requet："+request);
            String str = "服务端给客户端的响应:"+request;
            ctx.writeAndFlush(Unpooled.copiedBuffer(str.getBytes())).
                    addListener(ChannelFutureListener.CLOSE);//写完给客户端，关闭客户端连接
        }finally {
        }

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
        t.printStackTrace();
        ctx.close();
    }

}
