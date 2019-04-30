package com.zhengjy.rpc.core;

import com.caucho.hessian.io.Hessian2Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Jiyang.Zheng on 2019/1/14 17:17.
 */
public class HessianEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        serialize(byteArrayOutputStream, msg);
        byte[] body = byteArrayOutputStream.toByteArray();
        int dataLength = body.length;
        out.writeInt(dataLength);
        out.writeBytes(body);
    }


    public void serialize(OutputStream output, Object object) {
        Hessian2Output ho = new Hessian2Output(output);
        try {
            ho.startMessage();
            ho.writeObject(object);
            ho.completeMessage();
            ho.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
