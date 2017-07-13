package com.zhengjy.spring.filter;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * Created by zhengjy on 2017/7/13.
 */
public class GZipServletOutputStream extends ServletOutputStream{

// 定义压缩输出流

    private GZIPOutputStream gzipOutputStream;

    // 构造函数来创建压缩输出流
    // write 方法重写
    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);

    }

    public GZipServletOutputStream(ServletOutputStream servletOutputStreams) throws IOException{
        gzipOutputStream= new GZIPOutputStream(servletOutputStreams);

    }

    public GZIPOutputStream getGzipOutputStream() {
        return gzipOutputStream;
    }

    public void setGzipOutputStream(GZIPOutputStream gzipOutputStream) {
        this.gzipOutputStream= gzipOutputStream;

    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener listener) {

    }
}
