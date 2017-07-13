package com.zhengjy.spring.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * Created by zhengjy on 2017/7/13.
 */
public class HttpServletResponseWrapperTest extends HttpServletResponseWrapper {
    private ByteArrayOutputStream buffer = null;

    private ServletOutputStream out = null;

    private PrintWriter writer = null;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public HttpServletResponseWrapperTest(HttpServletResponse response) throws IOException {
        super(response);
        /**
         * 替换默认的输出端，作为response输出数据的存储空间（即真正存储数据的流）
         */
        buffer = new ByteArrayOutputStream();
        /**
         * response输出数据时是调用getOutputStream()和getWriter()方法获取输出流，再将数据输出到输出流对应的输出端的。
         * 此处指定getOutputStream()和getWriter()返回的输出流的输出端为buffer，即将数据保存到buffer中。
         */
        out = new WapperedOutputStream(buffer);
        writer = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
    }


    //重载父类获取outputstream的方法

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return out;

    }


    //重载父类获取writer的方法
    @Override
    public PrintWriter getWriter() throws UnsupportedEncodingException {
        return writer;

    }

    /**
     * 这是将数据输出的最后步骤
     * @throws IOException

     */
    @Override
    public void flushBuffer() throws IOException {
        if (out != null) {
            out.flush();
        }
        if (writer != null) {
            writer.flush();
        }

    }

    @Override
    public void reset() {
        buffer.reset();

    }

    public byte[] getResponseData() throws IOException {
        flushBuffer();//将out、writer中的数据强制输出到WapperedResponse的buffer里面，否则取不到数据
        return buffer.toByteArray();

    }

}

//内部类，对ServletOutputStream进行包装，指定输出流的输出端

class WapperedOutputStream extends ServletOutputStream {
    private ByteArrayOutputStream bos = null;
    public WapperedOutputStream(ByteArrayOutputStream stream) throws IOException {
        bos = stream;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {
    }

    //将指定字节写入输出流bos

    @Override
    public void write(int b) throws IOException {
        bos.write(b);

    }
}