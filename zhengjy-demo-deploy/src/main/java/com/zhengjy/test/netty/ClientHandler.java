package com.zhengjy.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by zhengjy on 2017/3/25.
 */
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ((ByteBuf)msg).release();
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
        t.printStackTrace();
        ctx.close();
    }
}
