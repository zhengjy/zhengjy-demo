//package com.zhengjy.test.netty;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.util.ReferenceCountUtil;
//
//import java.io.UnsupportedEncodingException;
//
///**
// * Created by zhengjy on 2017/3/25.
// */
//public class ClientHandler extends ChannelHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg){
//        try{
//            ByteBuf buf = ((ByteBuf)msg);
//            byte[] data = new byte[buf.readableBytes()];
//            buf.readBytes(data);
//            String request = new String(data,"utf-8");
//            System.out.println("客户端："+request);
//        } catch (UnsupportedEncodingException e) {
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
//    }
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
//        t.printStackTrace();
//        ctx.close();
//    }
//}
