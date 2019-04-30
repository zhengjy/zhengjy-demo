package com.zhengjy.rpc.core.recv;

/**Rpc服务器消息处理
 * Created by Jiyang.Zheng on 2019/1/11 16:27.
 */
import com.zhengjy.rpc.model.MessageRequest;
import com.zhengjy.rpc.model.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Map;

public class MessageRecvHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Object> handlerMap;

    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        MessageResponse response = new MessageResponse();
        MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(request, response, handlerMap, ctx);
        //不要阻塞nio线程，复杂的业务逻辑丢给专门的线程池
        MessageRecvExecutor.submit(recvTask);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //网络有异常要关闭通道
        ctx.close();
    }
}
