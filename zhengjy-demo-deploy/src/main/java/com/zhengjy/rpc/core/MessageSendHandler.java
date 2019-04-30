package com.zhengjy.rpc.core;

import com.zhengjy.rpc.model.MessageRequest;
import com.zhengjy.rpc.model.MessageResponse;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rpc客户端处理模块
 * Created by Jiyang.Zheng on 2019/1/11 13:55.
 */
public class MessageSendHandler extends ChannelInboundHandlerAdapter {
    private Map<String,MessageCallBack> mapCallBack = new ConcurrentHashMap<>();

    private volatile Channel channel;

    private SocketAddress remoteAddr;

    public Channel getChannel(){
        return channel;
    }

    public SocketAddress getRemoteAddr(){
        return remoteAddr;
    }

    @Override
    public void channelActive(ChannelHandlerContext cxt) throws Exception {
        super.channelActive(cxt);
        this.remoteAddr = this.channel.remoteAddress();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext cxt) throws Exception {
        super.channelRegistered(cxt);
        this.channel = cxt.channel();
    }

    /**
     //     *从服务器接收到数据后调用
     //     * @param ctx
     //     * @param msg
     //     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageResponse rsponse = (MessageResponse) msg;
        String messageId= rsponse.getMessageId();
        MessageCallBack callBack = mapCallBack.get(messageId);
        if (callBack != null){
            mapCallBack.remove(messageId);
            callBack.over(rsponse);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public MessageCallBack sendRequest(MessageRequest request){
        MessageCallBack callBack = new MessageCallBack(request);
        mapCallBack.put(request.getMessageId(),callBack);
        //发送
        channel.writeAndFlush(request);
        return callBack;
    }


}
