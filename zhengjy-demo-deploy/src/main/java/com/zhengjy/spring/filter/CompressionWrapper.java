package com.zhengjy.spring.filter;

 import java.io.IOException;
 import java.io.OutputStreamWriter;
 import java.io.PrintWriter;
 import java.util.zip.GZIPOutputStream;
 import javax.servlet.ServletOutputStream;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpServletResponseWrapper;
/**
 * Created by zhengjy on 2017/7/13.
 */
public class CompressionWrapper extends  HttpServletResponseWrapper {

    private GZipServletOutputStream gzServletOutputStream;

    private PrintWriter printWriter;

    public CompressionWrapper(HttpServletResponse response) {

        super(response);

//TODO Auto-generated constructor stub

    }


    @Override

    public ServletOutputStream getOutputStream() throws IOException {

        if(printWriter!= null) {

            throw new IllegalStateException();

        }

        if(gzServletOutputStream == null) {

            gzServletOutputStream= new GZipServletOutputStream(

                    getResponse().getOutputStream());

        }

        return gzServletOutputStream;

    }



    @Override
// getWrite() 方法重写，实现压缩输出流
    public PrintWriter getWriter() throws IOException {

        if(gzServletOutputStream!= null) {

            throw new IllegalStateException();

        }

        if(printWriter == null) {

            gzServletOutputStream= new GZipServletOutputStream(getResponse().getOutputStream());

            OutputStreamWriter osw = new OutputStreamWriter(gzServletOutputStream,getResponse().getCharacterEncoding());

            printWriter= new PrintWriter(osw);

        }

        return printWriter;

    }


    @Override

    public void setContentLength(int len) {}

    public GZIPOutputStream getGZIPOutputStream() {

        if(this.gzServletOutputStream == null) {
            return null;

        }
        return this.gzServletOutputStream.getGzipOutputStream();

    }
}
