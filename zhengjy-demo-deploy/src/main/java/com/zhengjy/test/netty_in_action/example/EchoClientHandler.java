//package com.zhengjy.test.netty_in_action.example;
//
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.ByteBufUtil;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerAdapter;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.util.CharsetUtil;
//
///**
// * Created by zhengjy on 2017/10/27 17:19
// */
//public class EchoClientHandler extends ChannelHandlerAdapter {
//
//    /**
//     * 客户端连接服务器后被调用
//     * @param ctx
//     */
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) {
//        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
//    }
//
//    /**
//     *从服务器接收到数据后调用
//     * @param ctx
//     * @param msg
//     */
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg){
//        ByteBuf buf = ((ByteBuf)msg);
//        System.out.println("Client receiverd :" + ByteBufUtil.hexDump(buf));
//    }
//
//    /**
//     * 发生异常时被调用
//     * @param ctx
//     * @param thx
//     */
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx,Throwable thx){
//        thx.printStackTrace();
//        ctx.close();
//    }
//
//}
